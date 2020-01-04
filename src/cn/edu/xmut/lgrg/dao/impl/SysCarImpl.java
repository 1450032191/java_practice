package cn.edu.xmut.lgrg.dao.impl;

import cn.edu.xmut.lgrg.dao.SysCarDAO;
import cn.edu.xmut.lgrg.util.MySqlUtil;
import cn.edu.xmut.lgrg.util.UserUtil;

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
public class SysCarImpl implements SysCarDAO {
    private Connection conn = null;

    @Override
    public List<Map<String, String>> getAllShoes(HttpServletRequest request) throws Exception {

        //创建一个存储所有购物车数据的集合
        List<Map<String, String>> res = new ArrayList<>();
        //获取用户
        String userId = UserUtil.getUserId(request);


        conn = MySqlUtil.getCon();


        String sql = "select * from sys_car where user_id=" + userId;

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();

        while (rs.next()) {
            Map<String, String> com_info = new HashMap<>();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                com_info.put(metaData.getColumnName(i + 1), rs. getString(i + 1));
            }

            res.add(com_info);
        }

        return res;

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
            return false;
        }
    }
}
