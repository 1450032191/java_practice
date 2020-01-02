package cn.edu.xmut.lgrg.dao.impl;

import cn.edu.xmut.lgrg.dao.CommodityDao;
import cn.edu.xmut.lgrg.entity.SysCommodity;
import cn.edu.xmut.lgrg.util.ConnDBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommodityImpl implements CommodityDao {

    @Override
    public void addComm(SysCommodity comm) throws Exception {
        String sql = "insert into sys_commodity(com_name,com_price,com_create_time,com_brand_id,com_category_id,detail_info,is_vip,com_img) values(?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = null;
        ConnDBUtil connDB = new ConnDBUtil();
        connDB.OpenConn();
        try {
            pstm = connDB.createPStmt(sql);
            pstm.setString(1, comm.getComName());
            pstm.setDouble(2, comm.getComPrice());
            pstm.setString(3, comm.getComCreateTime());
            pstm.setString(4, comm.getComBrandId());
            pstm.setString(5, comm.getComCategoryId());
            pstm.setString(5, comm.getDetailInfo());
            pstm.setString(6, comm.getIsVip());
            pstm.setString(7, comm.getComImg());
            pstm.executeUpdate();
            pstm.close();
        } catch (Exception e) {
            throw new Exception("数据操作错误！");
        } finally {
            connDB.closeConn();
        }
    }

    @Override
    public void deleteComm(SysCommodity comm) throws Exception {
        String sql = "delete from sys_commodity where com_id=?";
        PreparedStatement pstm = null;
        ConnDBUtil connDB = new ConnDBUtil();
        connDB.OpenConn();
        try {
            pstm = connDB.createPStmt(sql);
            pstm.setString(1, comm.getComId());
            pstm.executeUpdate();
            pstm.close();
        } catch (Exception e) {
            throw new Exception("操作错误！");
        } finally {
            connDB.closeConn();
        }
    }

    @Override
    public void updateComm(SysCommodity comm) throws Exception {
        String sql = "update sys_commodity set com_name=?,com_price=?,com_brand_id=?,com_category_id=?,detail_info=?,is_vip=? where com_id=?";
        PreparedStatement pstm = null;
        ConnDBUtil connDB = new ConnDBUtil();
        try {
            pstm = connDB.createPStmt(sql);
            pstm.setString(1, comm.getComName());
            pstm.setDouble(2, comm.getComPrice());
            pstm.setString(3, comm.getComBrandId());
            pstm.setString(4, comm.getComCategoryId());
            pstm.setString(5, comm.getDetailInfo());
            pstm.setString(6, comm.getIsVip());
            pstm.setString(7, comm.getComId());
            pstm.executeUpdate();
            pstm.close();
        } catch (Exception e) {
            throw new Exception("操作错误！");
        } finally {
            connDB.closeConn();
        }
    }

    @Override
    public SysCommodity selectComm(int comId) throws Exception {
        SysCommodity comm = null;
        String sql = "Select com_id,com_name,com_price,com_create_time,com_brand_id,com_category_id,detail_info,is_vip,com_img from sys_commodity where com_id=?";
        PreparedStatement pstm = null;
        ConnDBUtil connDB = new ConnDBUtil();
        try {
            pstm = connDB.createPStmt(sql);
            pstm.setString(1, comm.getComId());
            ResultSet rs = connDB.pstmtQuery();
            if (rs.next()) {
                comm = new SysCommodity();
                comm.setComId(rs.getString(1));
                comm.setComName(rs.getString(2));
                comm.setComPrice(rs.getDouble(3));
                comm.setComCreateTime(rs.getString(4));
                comm.setComBrandId(rs.getString(5));
                comm.setComCategoryId(rs.getString(6));
                comm.setDetailInfo(rs.getString(7));
                comm.setIsVip(rs.getString(8));
                comm.setComImg(rs.getString(9));
            }
            rs.close();
            pstm.close();
        } catch (Exception e) {
            throw new Exception("操作错误！");
        } finally {
            connDB.closeConn();
        }
        return comm;
    }

    @Override
    public List selectAllComm(int comId) throws Exception {
        List ListAll = new ArrayList();
        String sql = "select id,title,author,content from note1";
        PreparedStatement pstmt = null;
        ConnDBUtil connDB = null;
        connDB = new ConnDBUtil();
        try {
            connDB.OpenConn();
            pstmt = connDB.createPStmt(sql);
            ResultSet rs = connDB.pstmtQuery();
            while (rs.next()) {
                SysCommodity comm = new SysCommodity();
                comm.setComId(rs.getString(1));
                comm.setComName(rs.getString(2));
                comm.setComPrice(rs.getDouble(3));
                comm.setComCreateTime(rs.getString(4));
                comm.setComBrandId(rs.getString(5));
                comm.setComCategoryId(rs.getString(6));
                comm.setDetailInfo(rs.getString(7));
                comm.setIsVip(rs.getString(8));
                comm.setComImg(rs.getString(9));
                ListAll.add(comm);
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception("操作中出现错误！！！");
        } finally {
            connDB.closeConn();
        }
        return ListAll;
    }
}
