package cn.edu.xmut.lgrg.dao.impl;

import cn.edu.xmut.lgrg.dao.OrderDao;
import cn.edu.xmut.lgrg.util.MySqlUtil;
import cn.edu.xmut.lgrg.util.ResultUtil;
import cn.edu.xmut.lgrg.util.UserUtil;

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
public class OrderImpl implements OrderDao {

    private Connection conn = null;


    /**
     * 获取该用户的下单信息
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, String>> getOrderByUserId(HttpServletRequest request) throws Exception {

        //获取用户ID
        String userId = UserUtil.getUserId(request);

        //数据库查询
        conn = MySqlUtil.getCon();
        String sql="select * from sys_order  where user_id="+userId;
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();

        //创建一个存储该用户所有订单信息的集合
        List<Map<String, String>> res = new ArrayList<>();

        //遍历订单表，查找该用户订单
        while (rs.next()){
            Map<String, String> order_info = new HashMap<>();

            for (int i = 0; i <metaData.getColumnCount(); i++) {
                order_info.put(metaData.getColumnName(i+1),rs.getString(i+1));
            }
            res.add(order_info);
        }
        return res;
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
