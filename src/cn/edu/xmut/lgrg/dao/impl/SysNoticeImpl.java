package cn.edu.xmut.lgrg.dao.impl;

import cn.edu.xmut.lgrg.dao.SysNoticeDao;
import cn.edu.xmut.lgrg.entity.Brand;
import cn.edu.xmut.lgrg.entity.SysNotice;
import cn.edu.xmut.lgrg.entity.SysUserAddr;
import cn.edu.xmut.lgrg.util.MySqlUtil;
import cn.edu.xmut.lgrg.util.ResultSetUtil;
import cn.edu.xmut.lgrg.util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SysNoticeImpl implements SysNoticeDao {
    public void addNotice(String[] addr, HttpServletRequest request) throws Exception {
        String sql = "insert into sys_notice (user_id,USER_headline,user_notice,notice_time)" +
                " value(?,?,?,?)";
        PreparedStatement pstm = null;
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        try {
            pstm = MySqlUtil.getCon().prepareStatement(sql);
            pstm.setString(1, addr[0]);
            pstm.setString(2, addr[1]);
            pstm.setString(3, addr[2]);
            pstm.setString(4, sdf.format(date));
            pstm.executeUpdate();
            pstm.close();
        } catch (Exception e) {
            throw new Exception("数据操作错误！");
        } finally {
        }
    }

    public List<String[]> selectNotice(HttpServletRequest request) throws Exception {
        String userId = UserUtil.getUserId(request);
        String sql = "SELECT user_id,user_headline,user_notice,notice_time FROM sys_notice  ";
        PreparedStatement pstmt = null;
        pstmt = MySqlUtil.getCon().prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<String[]> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new String[]{rs.getString(1), rs.getString(2)
                    , rs.getString(3), rs.getString(4)});
        }
        rs.close();
        pstmt.close();

        return list;


    }

}
