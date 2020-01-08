package cn.edu.xmut.lgrg.dao.impl;

import cn.edu.xmut.lgrg.annotation.ZnService;
import cn.edu.xmut.lgrg.dao.OrderDao;
import cn.edu.xmut.lgrg.dao.impl.sysenum.OrderStatus;
import cn.edu.xmut.lgrg.entity.*;
import cn.edu.xmut.lgrg.util.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author azx
 * @create 2020-01-06-9:34
 */
@ZnService
public class OrderImpl implements OrderDao {

    private Connection conn = null;


    /**
     * 获取该用户的下单信息
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public List<SysOrder> getOrderByUserId(HttpServletRequest request) throws Exception {
        //获取用户ID
        String userId = UserUtil.getUserId(request);
        SysCommodityImpl commodityService = BeanUtil.getInstance(SysCommodityImpl.class);

        //数据库查询
        conn = MySqlUtil.getCon();
        String sql="select * from sys_order  where user_id="+userId;
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        //遍历订单表，查找该用户订单
        List<SysOrder> list = ResultSetUtil.getArray(rs,SysOrder.class);
        for (int i = 0; i < list.size(); i++) {
            SysOrder order = list.get(i);
            order.setOrderStatusText(OrderStatus.getOrderStatus(order.getOrderStatus()));
            //查询订单详情
            sql = "select * from sys_order_detail where order_no = ? and order_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,order.getOrderNo());
            ps.setString(2,order.getOrderId());
            rs = ps.executeQuery();

            List<OrderItem> orderItems = ResultSetUtil.getArray(rs,OrderItem.class);
            for (int j = 0; j < orderItems.size(); j++) {
                orderItems.get(j).setCommodity(commodityService.selectComm(orderItems.get(j).getComId()));
            }
            order.setComCount(orderItems.size());
            order.setOrderItems(orderItems);
        }
        return list;
    }

    public void generateOrder(HttpServletRequest request) throws Exception {
        String userId = UserUtil.getUserId(request);
        PageData params = new PageData(request);
        String payList = params.getString("payList");
        String uaId = params.getString("uaId");
        SysUserAddrlmpl addrService = BeanUtil.getInstance(SysUserAddrlmpl.class);
        SysUserAddr userAddr = addrService.selOnly(uaId);
        if(StringUtil.isNull(payList)||userAddr == null){
            throw new Exception("参数不存在~");
        }
        String[] comIds = payList.split(",");
        if(comIds.length<=0){
            throw new Exception("参数不存在~");
        }
        List<OrderItem> comItem = new ArrayList<>();
        List<SysCommodity> comList = new ArrayList<>();

        SysCarImpl carService = BeanUtil.getInstance(SysCarImpl.class);
        List<SysCar> carList = carService.getAllShoes(request);
        Map<String,Integer> comCountMap = new HashMap<String,Integer>();
        for (int i = 0; i < carList.size(); i++) {
            String id = carList.get(i).getComId();
            String count = carList.get(i).getComCount();
            try {
                comCountMap.put(id,Integer.valueOf(count));
            }catch (Exception e){}
        }
        SysCommodityImpl commodityService = BeanUtil.getInstance(SysCommodityImpl.class);
        Integer allCount = 0;
        Double allPrice = 0.0;
        for (int i = 0; i < comIds.length; i++) {
            if(!StringUtil.isNull(comIds[i])){
                //查询
                SysCommodity commodityItem = commodityService.selectComm(comIds[i]);
                if(commodityItem!=null){
                    comList.add(commodityItem);
                    Integer comCount = comCountMap.get(commodityItem.getComId());

                    //验证库存
                    Integer stock = Integer.parseInt(commodityItem.getComStock());
                    if(comCount>stock){
                        throw new Exception("商品库存不足~");
                    }

                    OrderItem orderItem = new OrderItem();
                    Double comPrice = Double.valueOf(commodityItem.getComPrice());

                    allCount+=comCount;
                    Double allComPrice = comCount * comPrice;
                    allPrice += allComPrice;

                    orderItem.setComPrice(String.valueOf(comPrice));
                    orderItem.setComCount(String.valueOf(comCount));
                    orderItem.setComId(commodityItem.getComId());
                    orderItem.setComTotalPrice(String.valueOf(allComPrice));
                    comItem.add(orderItem);
                }
            }
        }
        if(comItem.size()<=0){
            throw new Exception("参数不存在~");
        }
        //保存订单信息
        String orderNo = OrderUtil.getOrderNoByUUID();
        SysOrder order = new SysOrder();
        order.setAddrId(uaId);
        order.setOrderNo(orderNo);
        order.setOrderPaymethod("1");
        order.setOrderPrice(String.valueOf(allPrice));
        order.setUserId(userId);

        //开启操作数据库
        Connection con = MySqlUtil.getCon();
        con.setAutoCommit(false);

        String sql = "insert into sys_order(order_no,create_time,user_id,addr_id,order_price,order_paymethod,order_status)" +
                "values(?,now(),?,?,?,?,1)";
        PreparedStatement pre = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        pre.setString(1,orderNo);
        pre.setString(2,userId);
        pre.setString(3,uaId);
        pre.setString(4,order.getOrderPrice());
        pre.setString(5,order.getOrderPaymethod());
        pre.executeUpdate();
        ResultSet rs = pre.getGeneratedKeys();
        rs.next();
        int orderId = rs.getInt(1);
        rs.close();
        pre.close();

        //插入item数据
        for (int i = 0; i < comItem.size(); i++) {
            OrderItem orderItem = comItem.get(i);
            sql = "insert into sys_order_detail(order_no,order_id,com_id,com_price,com_count,com_total_price) values (?,?,?,?,?,?)";
            pre = con.prepareStatement(sql);
            pre.setString(1,orderNo);
            pre.setInt(2,orderId);
            pre.setString(3,orderItem.getComId());
            pre.setString(4,orderItem.getComPrice());
            pre.setString(5,orderItem.getComCount());
            pre.setString(6,orderItem.getComTotalPrice());
            pre.executeUpdate();

            //删除库存
            sql = "update sys_commodity set com_stock = com_stock - ? where com_id = ?";
            pre = con.prepareStatement(sql);
            pre.setInt(1,Integer.valueOf(orderItem.getComCount()));
            pre.setString(2,orderItem.getComId());
            pre.executeUpdate();

            //删除购物
            sql = "delete from sys_car where com_id = ? and user_id = ?";
            pre = con.prepareStatement(sql);
            pre.setString(1,orderItem.getComId());
            pre.setString(2,userId);
            pre.executeUpdate();

        }

        con.commit();
        con.setAutoCommit(true);
    }

    /**
     * 修改支付订单状态
     * @param order_id
     * @return
     * @throws Exception
     */
    @Override
    public boolean changePaymentOrderStatue(String order_id) throws Exception {

        //1.查询是否存在该订单
        boolean result = existOrder(order_id);

        conn=MySqlUtil.getCon();
        if (result){
            //2.获取订单的状态
            int orderStatus = getOrderStatus(order_id);
            if (orderStatus==1){//订单支付
                String sql="update sys_order set order_status=2 where order_id="+order_id;

                //3.返回执行的结果
                return updateSql(sql);
            }else{
                throw new Exception("订单状态有误");
            }
        }else{
            throw new Exception("不存在该订单");
        }
    }


    /**
     * 修改发货订单的状态
     * @param order_id
     * @return
     * @throws Exception
     */
    @Override
    public boolean changeSendoutOrderStatue(String order_id) throws Exception {
        //1.查询是否存在该订单
        boolean result = existOrder(order_id);

        conn=MySqlUtil.getCon();
        if (result){
            //2.获取订单的状态
            int orderStatus = getOrderStatus(order_id);
            if (orderStatus==2){
                String sql="update sys_order set order_status=3 where order_id="+order_id;

                //3.返回执行的结果
                return updateSql(sql);
            }else{
                throw new Exception("订单状态有误");
            }
        }else{
            throw new Exception("不存在该订单");
        }
    }

    /**
     * 修改确认订单的状态
     * @param order_id
     * @return
     * @throws Exception
     */
    @Override
    public boolean changeConfirmOrderStatue(String order_id) throws Exception {
        //1.查询是否存在该订单
        boolean result = existOrder(order_id);

        conn=MySqlUtil.getCon();
        if (result){
            //2.获取订单的状态
            int orderStatus = getOrderStatus(order_id);
            if (orderStatus==3){
                String sql="update sys_order set order_status=4 where order_id="+order_id;

                //3.返回执行的结果
                return updateSql(sql);
            }else{
                throw new Exception("订单状态有误");
            }
        }else{
            throw new Exception("不存在该订单");
        }
    }


    /**
     * 执行更新语句
     * @param sql
     * @return
     * @throws SQLException
     */
    private boolean updateSql(String sql) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(sql);

        int i = ps.executeUpdate();

        if (i>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 是否存在该订单
      * @param OrderId
     * @return
     */
    public boolean existOrder(String OrderId) throws Exception {
        String sql="select * from sys_order where order_id="+OrderId;

        conn = MySqlUtil.getCon();

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();

        if (resultSet.next()){
            return  true;
        }else{
            return false;
        }
    }


    /**
     * 获取订单状态
     * @param orderId
     * @return
     * @throws Exception
     */
    public int getOrderStatus(String orderId) throws Exception {

        int  order_status=0;
        String sql="select order_status from sys_order where order_id= "+orderId;

        conn=MySqlUtil.getCon();

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        //只有一条语句使用if来执行
        if (rs.next()){
            order_status = rs.getInt("order_status");
        }

        return order_status;
    }

}
