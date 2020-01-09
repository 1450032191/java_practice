 package cn.edu.xmut.lgrg.dao.impl;

import cn.edu.xmut.lgrg.annotation.ZnService;
import cn.edu.xmut.lgrg.dao.SysCarDAO;
import cn.edu.xmut.lgrg.dao.SysCommodityDao;
import cn.edu.xmut.lgrg.entity.SysCar;
import cn.edu.xmut.lgrg.entity.SysCommodity;
import cn.edu.xmut.lgrg.util.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author azx
 * @create 2019-12-31-9:35
 */
@ZnService
public class SysCarImpl implements SysCarDAO {
    private Connection conn = null;

    public Integer getCommodityCount(HttpServletRequest request,String comId) throws Exception {
        String userId = UserUtil.getUserId(request);
        String sql = "select * from sys_car where user_id=" + userId + " and com_id = ?";
        Connection connection = MySqlUtil.getCon();
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1,comId);
        ResultSet resultSet = pre.executeQuery();
        List<SysCar> sysCars= ResultSetUtil.getArray(resultSet,SysCar.class);
        if(sysCars.size()>0){
            try {
                return Integer.valueOf(sysCars.get(0).getComCount());
            }catch (Exception e){
                return 0;
            }
        }
        return 0;
    }

    public Integer getCarCount(HttpServletRequest request) throws Exception {
        String userId = UserUtil.getUserId(request);
        conn = MySqlUtil.getCon();
        String sql = "select * from sys_car where user_id=" + userId;

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<SysCar> cars = ResultSetUtil.getArray(rs,SysCar.class);
        Integer allCount = 0;
        for (int i = 0; i < cars.size(); i++) {
            try {
                Integer count = Integer.valueOf(cars.get(i).getComCount());
                allCount=allCount+count;
            }catch (Exception e){}
        }
        return allCount;
    }

    @Override
    public List<SysCar> getAllShoes(HttpServletRequest request) throws Exception {

        //创建一个存储所有购物车数据的集合
        List<Map<String, String>> res = new ArrayList<>();
        //获取用户
        String userId = UserUtil.getUserId(request);

        conn = MySqlUtil.getCon();


        String sql = "select * from sys_car where user_id=" + userId;

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();


        List<SysCar> cars = ResultSetUtil.getArray(rs,SysCar.class);
        SysCommodityImpl commodityService = BeanUtil.getInstance(SysCommodityImpl.class);
        for (int i = 0; i < cars.size(); i++) {
            SysCar car = cars.get(i);
            SysCommodity commodity = commodityService.selectComm(car.getComId());
            car.setCommodity(commodity);

            Double comPrice = Double.valueOf(commodity.getComPrice());
            Integer comCount = Integer.valueOf(car.getComCount());
            car.setAllComPrice(comPrice * comCount);
        }
        return cars;

    }

    @Override
    public boolean deleteAllCommodity(HttpServletRequest request) throws Exception {
        //获取用户
        String userId = UserUtil.getUserId(request);

        conn = MySqlUtil.getCon();
        //清空该用户的所有购物车信息
        String sql = "delete  from sys_car where user_id=" + userId;

        PreparedStatement ps = conn.prepareStatement(sql);
        int result = ps.executeUpdate();

        if (result!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteById(HttpServletRequest request ,int comId) throws Exception {

        //获取用户
        String userId = UserUtil.getUserId(request);

        conn = MySqlUtil.getCon();

        //删除该用户某样商品语句
        String sql = "delete  from sys_car where user_id="+userId+" and com_id="+comId;

        PreparedStatement ps = conn.prepareStatement(sql);
        int result = ps.executeUpdate();

        if (result!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean changeQuantity(HttpServletRequest request ,int comId, int quantity) throws Exception {
        //获取用户
        String userId = UserUtil.getUserId(request);
        conn = MySqlUtil.getCon();
        int result=0;
        if (quantity==0){
            //删除该用户某样商品
            String delsql = "delete  from sys_car where user_id=" + userId +" and com_id="+comId;
            PreparedStatement ps = conn.prepareStatement(delsql);
            result = ps.executeUpdate();
        }else{
            //更新该商品的数量
            String updatesql="update sys_car set com_quantity="+quantity+
                    " where user_id=" + userId +" and com_id="+comId;
            PreparedStatement ps=conn.prepareStatement(updatesql);
            result = ps.executeUpdate();
        }
        if (result!=0){
            return true;
        }else{
            String sel = "insert into sys_car(user_id,com_price,com_id,com_quantity) values(?,?,?,?)";
            SysCommodityImpl commodityService = BeanUtil.getInstance(SysCommodityImpl.class);
            SysCommodity sysCommodity = commodityService.selectComm(Integer.valueOf(comId));
            if(sysCommodity == null){
                throw new Exception("商品不存在~");
            }
            try {
                String s = sysCommodity.getComStock();
                Integer stock = Integer.valueOf(s);
                if(stock<quantity){
                    throw new Exception("库存不足~");
                }
                Connection connection = MySqlUtil.getCon();
                PreparedStatement pre = connection.prepareStatement(sel);
                pre.setString(1,userId);
                pre.setString(2,String.valueOf(sysCommodity.getComPrice()));
                pre.setString(3,String.valueOf(comId));
                pre.setString(4,String.valueOf(quantity));
                if(pre.executeUpdate()>0){
                    return true;
                }
            }catch (Exception e){

                throw new Exception(e.getMessage());
            }
        }
        return false;
    }


}
