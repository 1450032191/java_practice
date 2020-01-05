package cn.edu.xmut.lgrg.dao.impl;

import cn.edu.xmut.lgrg.annotation.ZnService;
import cn.edu.xmut.lgrg.dao.CommodityCategoryDao;
import cn.edu.xmut.lgrg.entity.CommodityCategory;
import cn.edu.xmut.lgrg.entity.PageData;
import cn.edu.xmut.lgrg.entity.SysUser;
import cn.edu.xmut.lgrg.util.ConnDBUtil;
import cn.edu.xmut.lgrg.util.MySqlUtil;
import cn.edu.xmut.lgrg.util.ResultSetUtil;
import cn.edu.xmut.lgrg.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@ZnService
public class CommodityCategoryDaoImpl implements CommodityCategoryDao {
    @Override
    public void addCategory(CommodityCategory category) throws Exception {
        String sql = "INSERT INTO commodity_category(`name`,person_id,`status`,create_time) VALUES(?,?,?,?)";
        try {
            Connection con = MySqlUtil.getCon();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, category.getName());
            pstm.setString(2, category.getPersonId());
            pstm.setString(3, category.getStatus());
            pstm.setString(4, category.getCreateTime());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("增加数据错误！");
        }
    }

    @Override
    public void deleteCategory(CommodityCategory category) throws Exception {
        String sql = "delete from commodity_category where id=?";
        try {
            Connection con = MySqlUtil.getCon();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, category.getId());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("删除错误！");
        }
    }

    @Override
    public void updateCategory(CommodityCategory category) throws Exception {
        /*String sql = "update commodity_category set name=?,person_id=?,status=?,create_time=? where id=?";
        PreparedStatement pstm = null;
        ConnDBUtil connDB = new ConnDBUtil();
        try {
            pstm = connDB.createPStmt(sql);
            pstm.setString(1, category.getName());
            pstm.setString(2, category.getPersonId());
            pstm.setString(3, category.getStatus());
            pstm.setString(4, category.getCreateTime());
            pstm.executeUpdate();
            pstm.close();
        } catch (Exception e) {
            throw new Exception("更新错误！");
        } finally {
            connDB.closeConn();
        }*/
        String categoryId = category.getId();
        if (StringUtil.isNull(categoryId)) {
            throw new Exception("参数异常~");
        }
        Integer count = 1;
        String baseSql = "update commodity_category set name=?,person_id=?,status=?,create_time=? set id=" + categoryId + "";

        if (!StringUtil.isNull(category.getName())) {
            baseSql += ",person_id='" + category.getPersonId() + "'";
            count++;
        }

        if (!StringUtil.isNull(category.getStatus())) {
            baseSql += ",status ='" + category.getStatus() + "'";
            count++;
        }

        if (!StringUtil.isNull(category.getCreateTime())) {
            baseSql += ",create_time = '" + category.getCreateTime() + "'";
            count++;
        }

        baseSql += "where id =" + categoryId + "";

        Connection con = MySqlUtil.getCon();
        PreparedStatement pre = con.prepareStatement(baseSql);
        pre.executeUpdate();
    }

    @Override
    public CommodityCategory selectCategory(int id) throws Exception {
        CommodityCategory category = null;
        String sql = "select id,name,person_id,status,create_time where id=?";
        try {
            Connection con = MySqlUtil.getCon();
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, category.getId());
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                category = new CommodityCategory();
                category.setId(rs.getString(1));
                category.setName(rs.getString(2));
                category.setPersonId(rs.getString(3));
                category.setStatus(rs.getString(4));
                category.setCreateTime(rs.getString(5));
            }
            rs.close();
            pre.close();
        } catch (Exception e) {
            throw new Exception("根据id查询错误！");
        }
        return category;
    }

    @Override
    public List selectAllCategory() throws Exception {
        List ListAll = new ArrayList();
        String sql = "select * from commodity_category";
        PreparedStatement pstmt = null;
        try {
            Connection con = MySqlUtil.getCon();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                CommodityCategory comm = new CommodityCategory();
                comm.setId(rs.getString(1));
                comm.setName(rs.getString(2));
                comm.setPersonId(rs.getString(3));
                comm.setStatus(rs.getString(4));
                comm.setCreateTime(rs.getString(5));
                ListAll.add(comm);
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception("查询全部错误！！！");
        }
        return ListAll;
    }
}

