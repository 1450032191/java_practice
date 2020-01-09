package cn.edu.xmut.lgrg.dao.impl;

import cn.edu.xmut.lgrg.annotation.ZnService;
import cn.edu.xmut.lgrg.annotation.ZnSqlField;
import cn.edu.xmut.lgrg.entity.PageData;
import cn.edu.xmut.lgrg.entity.SysNotic;
import cn.edu.xmut.lgrg.entity.SysUser;
import cn.edu.xmut.lgrg.util.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * @Auther: ZiNan
 * @Date: 2020/1/9 08:32
 * @Description:
 */
@ZnService
public class SysNoticImpl {

    public PageData list(HttpServletRequest request) throws Exception {
        String sql = "SELECT\n" +
                "\tsn.*,\n" +
                "\tsu.user_name AS send_user_name,\n" +
                "\t( SELECT COUNT( * ) FROM sys_notic_user WHERE notic_id = sn.id ) AS accept_count,\n" +
                "\t( SELECT COUNT( * ) FROM sys_notic_user WHERE notic_id = sn.id AND STATUS != 0 ) AS read_count,\n" +
                "\t( SELECT COUNT( * ) FROM sys_notic_user WHERE notic_id = sn.id AND STATUS = 0 ) AS no_read_count \n" +
                "FROM\n" +
                "\tsys_notice sn\n" +
                "\tLEFT JOIN sys_user su ON sn.user_id = su.user_id where (1=1)\n" ;

        PageData params = new PageData(request);
        String key = params.getString("key");
        if(!StringUtil.isNull(key)){
            sql += "and sn.user_headline LIKE CONCAT(CONCAT('%','"+key+"'),'%')";
            sql += "and sn.user_notice LIKE CONCAT(CONCAT('%','"+key+"'),'%')";
        }
        sql += "ORDER BY\n" +
                "\tnotice_time DESC";
        MySqlPageUtlil pageUtlil = new MySqlPageUtlil(sql,params);
        Map<String,Object> page = new PageData();
        page.put("total",pageUtlil.getTotal());
        page.put("pageSize",pageUtlil.getPageSize());
        page.put("pageIndex",pageUtlil.getPageIndex());
        PageData res = new PageData();
        res.put("page",page);
        res.put("list",pageUtlil.getArray(SysNotic.class));
        return res;
    }

    public void readAll(HttpServletRequest request) throws Exception {
        String userId = UserUtil.getUserId(request);

        String sql = "update sys_notic_user set status = '1' , read_time = now() where user_id = ? and status = 0";
        Connection con = MySqlUtil.getCon();
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1,userId);
        pre.executeUpdate();

    }

    public void delAll(HttpServletRequest request) throws Exception {
        String userId = UserUtil.getUserId(request);

        String sql = "update sys_notic_user set status = '-1' , read_time = now() where user_id = ? and status = 0";
        Connection con = MySqlUtil.getCon();
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1,userId);
        pre.executeUpdate();

    }

    public void read(HttpServletRequest request) throws Exception {
        String userId = UserUtil.getUserId(request);
        PageData params = new PageData(request);
        String noticId = params.getString("noticId");
        if (StringUtil.isNull(noticId)){
            throw new Exception("参数不完整~");
        }
        String sql = "update sys_notic_user set status = '1' , read_time = now() where user_id = ? and notic_id = ?";
        Connection con = MySqlUtil.getCon();
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1,userId);
        pre.setString(2,noticId);
        pre.executeUpdate();
    }

    public void del(HttpServletRequest request) throws Exception {
        String userId = UserUtil.getUserId(request);
        PageData params = new PageData(request);
        String noticId = params.getString("noticId");
        if (StringUtil.isNull(noticId)){
            throw new Exception("参数不完整~");
        }
        String sql = "update sys_notic_user set status = '-1' , read_time = now() where user_id = ? and notic_id = ?";
        Connection con = MySqlUtil.getCon();
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1,userId);
        pre.setString(2,noticId);
        pre.executeUpdate();
    }

    public void sendNotic(HttpServletRequest request) throws Exception {
        PageData params = new PageData(request);
        String userId = UserUtil.getUserId(request);
        String userHeadline = params.getString("userHeadline");
        String userNotice = params.getString("userNotice");
        String sendUser = params.getString("sendUser");
        if(StringUtil.isNull(userHeadline)||StringUtil.isNull(userNotice)){
            throw new Exception("参数异常~");
        }
        if(!"1".equals(sendUser)&&!"2".equals(sendUser)){
            throw new Exception("参数异常~");
        }

        String sql = "insert into sys_notice(user_id,user_headline,user_notice,notice_time) values(?,?,?,now())";
        Connection con = MySqlUtil.getCon();
        con.setAutoCommit(false);
        PreparedStatement pre = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pre.setString(1,userId);
        pre.setString(2,userHeadline);
        pre.setString(3,userNotice);
        pre.executeUpdate();
        ResultSet rs = pre.getGeneratedKeys();
        rs.next();
        String noticId = rs.getString(1);
        //查询用户(发送给所有用户)
        if("1".equals(sendUser)){
            sql = "select * from sys_user";
        }else {
            sql = "select * from sys_user where user_is_admin = 1";
        }
        pre = con.prepareStatement(sql);
        rs = pre.executeQuery();
        List<SysUser> userList = ResultSetUtil.getArray(rs,SysUser.class);
        sql = "insert into sys_notic_user(user_id,notic_id,status) values(?,?,0)";
        pre = con.prepareStatement(sql);
        for (int i = 0; i < userList.size(); i++) {
            SysUser user = userList.get(i);
            String uId = user.getUserId();
            pre.setString(1,uId);
            pre.setString(2,noticId);
            pre.execute();
        }
        con.commit();
        con.setAutoCommit(true);
    }

    public List<SysNotic> getUserNotic(HttpServletRequest request) throws Exception {
        String sql = "select snu.user_id as read_user_id,snu.notic_id,snu.status as read_status,snu.read_time,sn.notice_time,sn.user_headline,sn.user_notice,su.user_id as send_user_id,su.user_name as send_user_name " +
                "from sys_notic_user snu " +
                "left join sys_notice sn on snu.notic_id = sn.id " +
                "left join sys_user su on sn.user_id = su.user_id " +
                "where snu.user_id = ? and  snu.status != '-1' limit 10";
        String userId = UserUtil.getUserId(request);
        Connection con = MySqlUtil.getCon();
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1,userId);
        ResultSet rs = pre.executeQuery();
        List<SysNotic> noticList = ResultSetUtil.getArray(rs,SysNotic.class);
        return noticList;
    }
}
