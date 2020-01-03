package cn.edu.xmut.lgrg.dao.impl;

import cn.edu.xmut.lgrg.dao.CommodityCategoryDao;
import cn.edu.xmut.lgrg.entity.CommodityCategory;
import cn.edu.xmut.lgrg.util.ConnDBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommodityCategoryDaoImpl implements CommodityCategoryDao {
    @Override
    public void addCategory(CommodityCategory category) throws Exception {
        String sql = "INSERT INTO commodity_category(`name`,person_id,`status`,create_time) VALUES(?,?,?,?)";
        PreparedStatement pstm = null;
        ConnDBUtil connDB = new ConnDBUtil();
        connDB.OpenConn();
        try {
            pstm = connDB.createPStmt(sql);
            pstm.setString(1, category.getName());
            pstm.setString(2, category.getPersonId());
            pstm.setString(3, category.getStatus());
            pstm.setString(4, category.getCreateTime());
            pstm.executeUpdate();
            pstm.close();
        } catch (Exception e) {
            throw new Exception("增加数据错误！");
        } finally {
            connDB.closeConn();
        }
    }

    @Override
    public void deleteCategory(CommodityCategory category) throws Exception {
        String sql = "delete from commodity_category where id=?";
        PreparedStatement pstm = null;
        ConnDBUtil connDB = new ConnDBUtil();
        connDB.OpenConn();
        try {
            pstm = connDB.createPStmt(sql);
            pstm.setString(1, category.getId());
            pstm.executeUpdate();
            pstm.close();
        } catch (Exception e) {
            throw new Exception("删除错误！");
        } finally {
            connDB.closeConn();
        }
    }

    @Override
    public void updateCategory(CommodityCategory category) throws Exception {
        String sql = "update commodity_category set name=?,person_id=?,status=?,create_time=? where id=?";
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
        }
    }

    @Override
    public CommodityCategory selectCategory(int id) throws Exception {
        CommodityCategory category = null;
        String sql = "select id,name,person_id,status,create_time where id=?";
        PreparedStatement pstm = null;
        ConnDBUtil connDB = new ConnDBUtil();
        try {
            pstm = connDB.createPStmt(sql);
            pstm.setString(1, category.getId());
            ResultSet rs = connDB.pstmtQuery();
            if (rs.next()) {
                category = new CommodityCategory();
                category.setId(rs.getString(1));
                category.setName(rs.getString(2));
                category.setPersonId(rs.getString(3));
                category.setStatus(rs.getString(4));
                category.setCreateTime(rs.getString(5));
            }
            rs.close();
            pstm.close();
        } catch (Exception e) {
            throw new Exception("id查询错误！");
        } finally {
            connDB.closeConn();
        }
        return category;
    }

    @Override
    public List selectAllCategory() throws Exception {
        List ListAll = new ArrayList();
        String sql = "select * from commodity_category";
        PreparedStatement pstmt = null;
        ConnDBUtil connDB = null;
        connDB = new ConnDBUtil();
        try {
            connDB.OpenConn();
            pstmt = connDB.createPStmt(sql);
            ResultSet rs = connDB.pstmtQuery();
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
        } finally {
            connDB.closeConn();
        }
        return ListAll;
    }
}

