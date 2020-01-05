package cn.edu.xmut.lgrg.dao.impl;

import cn.edu.xmut.lgrg.dao.SysUserAddrDao;
import cn.edu.xmut.lgrg.dao.SysUserDao;
import cn.edu.xmut.lgrg.entity.SysCommodity;
import cn.edu.xmut.lgrg.entity.SysUserAddr;
import cn.edu.xmut.lgrg.util.ConnDBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SysUserAddrlmpl implements SysUserAddrDao {
    @Override
    public boolean addAddr(SysUserAddr addr) throws Exception {
        String sql = "insert into sys_user_addr(ua_name,ua_phone,ua_detail_addr,ua_addr) value(?,?,?,?)";
        PreparedStatement pstm = null;
        ConnDBUtil connDB = new ConnDBUtil();
        connDB.OpenConn();
        int i = 0;
        try {
            pstm = connDB.createPStmt(sql);
            pstm.setString(1, addr.getUaName());
            pstm.setString(2, addr.getUaPhone());
            pstm.setString(3, addr.getUaDetailAddr());
            pstm.setString(4, addr.getUaAddr());
            i = pstm.executeUpdate();
            pstm.close();
        } catch (Exception e) {
            throw new Exception("数据操作错误！");
        } finally {
            connDB.closeConn();
        }
        if (i > 0) {
            return true;
        }
        return false;
    }


    @Override
    public boolean deleteAddr(SysUserAddr addr) throws Exception {
        String sql = "DELETE FROM sys_user_addr WHERE id=?";
        PreparedStatement pstm = null;
        ConnDBUtil connDB = new ConnDBUtil();
        connDB.OpenConn();
        int i = 0;
        try {
            pstm = connDB.createPStmt(sql);
            pstm.setString(1, addr.getUaId());
            i = pstm.executeUpdate();
            pstm.close();
        } catch (Exception e) {
            throw new Exception("数据操作错误！");
        } finally {
            connDB.closeConn();
        }
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAddr(SysUserAddr addr) throws Exception {
        String sql = "upadre sys_ua_addr set ua_name=?,ua_phone=?,ua_detail_addr=?,ua_addr=? where id=?";
        PreparedStatement pstm = null;
        ConnDBUtil connDB = new ConnDBUtil();
        connDB.OpenConn();
        int i = 0;
        try {
            pstm = connDB.createPStmt(sql);
            pstm.setString(1, addr.getUaName());
            pstm.setString(2, addr.getUaPhone());
            pstm.setString(3, addr.getUaAddr());
            pstm.setString(4, addr.getUaDetailAddr());
            pstm.setString(4, addr.getUaId());
            i = pstm.executeUpdate();
            pstm.close();
        } catch (Exception e) {
            throw new Exception("数据操作错误！");
        } finally {
            connDB.closeConn();
        }
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public SysUserAddr selectAddr(int addrId) throws Exception {
        SysUserAddr addr = null;
        String sql = "Select ua_name,ua_phone,ua_detail_addr,ua_addr from sys_ua_addr where com_id=?";
        PreparedStatement pstm = null;
        ConnDBUtil connDB = new ConnDBUtil();
        try {
            pstm = connDB.createPStmt(sql);
            pstm.setString(1, addr.getUaId());
            ResultSet rs = connDB.pstmtQuery();
            if (rs.next()) {
                addr = new SysUserAddr();
                addr.setUaName(rs.getString(1));
                addr.setUaPhone(rs.getString(2));
                addr.setUaDetailAddr(rs.getString(3));
                addr.setUaAddr(rs.getString(4));

            }
            rs.close();
            pstm.close();
        } catch (Exception e) {
            throw new Exception("操作错误！");
        } finally {
            connDB.closeConn();
        }
        return addr;
    }

    @Override
    public List selectAllAddr(int addrId) throws Exception {
        List ListAll = new ArrayList();
        String sql = "Select ua_name,ua_phone,ua_detail_addr,ua_addr from sys_ua_addr where com_id=?";
        PreparedStatement pstmt = null;
        ConnDBUtil connDB = null;
        connDB = new ConnDBUtil();
        try {
            connDB.OpenConn();
            pstmt = connDB.createPStmt(sql);
            ResultSet rs = connDB.pstmtQuery();
            while (rs.next()) {
                SysUserAddr addr = new SysUserAddr();
                addr.setUaName(rs.getString(1));
                addr.setUaPhone(rs.getString(2));
                addr.setUaDetailAddr(rs.getString(3));
                addr.setUaAddr(rs.getString(4));

                ListAll.add(addr);
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


