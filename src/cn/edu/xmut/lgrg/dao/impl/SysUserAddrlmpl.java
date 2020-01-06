package cn.edu.xmut.lgrg.dao.impl;

import cn.edu.xmut.lgrg.annotation.ZnService;
import cn.edu.xmut.lgrg.dao.SysUserAddrDao;
import cn.edu.xmut.lgrg.dao.SysUserDao;
import cn.edu.xmut.lgrg.entity.PageData;
import cn.edu.xmut.lgrg.entity.SysCommodity;
import cn.edu.xmut.lgrg.entity.SysUserAddr;
import cn.edu.xmut.lgrg.util.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@ZnService
public class SysUserAddrlmpl implements SysUserAddrDao {
    public void addAddr(SysUserAddr addr,HttpServletRequest request) throws Exception {
        String sql = "insert into sys_user_addr(ua_name,ua_phone,ua_detail_addr,ua_addr,user_id,status,is_default)" +
                " value(?,?,?,?,?,1,?)";
        PreparedStatement pstm = null;
        try {
            SysUserAddr userAddr = getDefalutAddr(request);
            if(userAddr == null){
                addr.setIsDefault("1");
            }else {
                addr.setIsDefault("0");
            }
            List<SysUserAddr> count = selectAllAddr(request);
            pstm = MySqlUtil.getCon().prepareStatement(sql);
            pstm.setString(1, addr.getUaName());
            pstm.setString(2, addr.getUaPhone());
            pstm.setString(3, addr.getUaDetailAddr());
            pstm.setString(4, addr.getUaAddr());
            pstm.setString(5, UserUtil.getUserId(request));
            pstm.setString(6, addr.getIsDefault());
            pstm.executeUpdate();
            pstm.close();
        } catch (Exception e) {
            throw new Exception("数据操作错误！");
        } finally {
        }
    }


    public void deleteAddr(SysUserAddr addr) throws Exception {
        String sql = "DELETE FROM sys_user_addr WHERE ua_id=?";
        PreparedStatement pstm = null;
        SysUserAddr aa = selOnly(addr.getUaId());
        if("1".equals(aa.getIsDefault())){
            throw new Exception("默认不可删除~");
        }
        pstm = MySqlUtil.getCon().prepareStatement(sql);
        pstm.setString(1, addr.getUaId());
        pstm.executeUpdate();
        pstm.close();
    }

    public void updateAddr(SysUserAddr addr) throws Exception {
        String sql = "update sys_user_addr set ua_name=?,ua_phone=?,ua_detail_addr=?,ua_addr=? where ua_id=?";
        PreparedStatement pstm = null;
        int i = 0;
        pstm = MySqlUtil.getCon().prepareStatement(sql);
        pstm.setString(1, addr.getUaName());
        pstm.setString(2, addr.getUaPhone());
        pstm.setString(3, addr.getUaAddr());
        pstm.setString(4, addr.getUaDetailAddr());
        pstm.setString(5, addr.getUaId());
        i = pstm.executeUpdate();
        pstm.close();
    }

    public SysUserAddr selOnly(String uaId) throws Exception {
        String sql = "select ua_id,ua_name,ua_phone,ua_detail_addr,ua_addr,status,is_default,user_id " +
                " from sys_user_addr where ua_id=?";
        PreparedStatement pstmt = null;
        pstmt = MySqlUtil.getCon().prepareStatement(sql);
        pstmt.setString(1, uaId);
        ResultSet rs = pstmt.executeQuery();
        List<SysUserAddr> list = ResultSetUtil.getArray(rs,SysUserAddr.class);
        rs.close();
        pstmt.close();
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    public SysUserAddr getDefalutAddr(HttpServletRequest request) throws Exception {
        String userId = UserUtil.getUserId(request);
        String sql = "select ua_id,ua_name,ua_phone,ua_detail_addr,ua_addr,status,is_default,user_id from sys_user_addr " +
                "where is_default = 1 and user_id=?";
        PreparedStatement pstmt = null;
        pstmt = MySqlUtil.getCon().prepareStatement(sql);
        pstmt.setString(1, userId);
        ResultSet rs = pstmt.executeQuery();
        List<SysUserAddr> list =  ResultSetUtil.getArray(rs,SysUserAddr.class);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    public void setDeatault(HttpServletRequest request, PageData params)throws Exception{
        String uaId = params.getString("uaId");
        if(StringUtil.isNull(uaId)){
            throw new Exception("参数异常~");
        }
        String sql = "update sys_user_addr set is_default = 0 where user_id = ?";
        String userId = UserUtil.getUserId(request);
        Connection con = MySqlUtil.getCon();
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1,userId);
        int count = pre.executeUpdate();
        if(count>=0){
            sql = "update sys_user_addr set is_default = 1 where ua_id = ? and user_id = ?";
            pre = con.prepareStatement(sql);
            pre.setString(1,uaId);
            pre.setString(2,userId);
            pre.execute();
        }
    }

    public List<SysUserAddr> selectAllAddr(HttpServletRequest req) throws Exception {
        List<SysUserAddr> ListAll = new ArrayList();
        String sql = "Select ua_id,ua_name,ua_phone,ua_detail_addr,ua_addr,status,is_default,user_id from sys_user_addr where user_id=?";
        PreparedStatement pstmt = null;
        Connection connection = MySqlUtil.getCon();
        pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, UserUtil.getUserId(req));
        ResultSet rs = pstmt.executeQuery();
        return ResultSetUtil.getArray(rs,SysUserAddr.class);
    }
}


