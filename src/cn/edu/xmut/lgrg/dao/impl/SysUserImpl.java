package cn.edu.xmut.lgrg.dao.impl;

import cn.edu.xmut.lgrg.annotation.ZnService;
import cn.edu.xmut.lgrg.dao.SysUserDao;
import cn.edu.xmut.lgrg.entity.PageData;
import cn.edu.xmut.lgrg.entity.SysUser;
import cn.edu.xmut.lgrg.entity.SysUserAddr;
import cn.edu.xmut.lgrg.util.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * @Auther: ZiNan
 * @Date: 2019/12/30 18:45
 * @Description:
 */
@ZnService
public class SysUserImpl implements SysUserDao {
    public SysUser login(HttpServletRequest request,String userName, String userPass,String checkCode) throws Exception {
        if(StringUtil.isNull(userName)||StringUtil.isNull(userPass)||StringUtil.isNull(checkCode)){
            throw new Exception("参数不完整~");
        }
        if(!CodeUtil.checkCode(request,checkCode)){
            throw new Exception("验证码错误~");
        }
        userPass = getUserPass(userPass);
        SysUser sysUser = null;
        //如果是用户手机号
        sysUser = loginByPhoneAndPass(userName,userPass);
        if(sysUser == null){
            sysUser = loginByUserIdAndPass(userName,userPass);
        }
        if(sysUser == null){
            sysUser = loginByPhoneAndPass(userName,userPass);
        }
        if(sysUser == null){
            throw new Exception("请检查账号密码是否正确~");
        }
        saveUserLoginStatus(request,sysUser);
        return sysUser;
    }

    public void setUserStatus(PageData params) throws Exception {
        String opUserId = params.getString("userId");
        SysUser opSysUser = getUserByUserId(opUserId);
        if(opSysUser == null){
            throw new Exception("操作对象不存在~");
        }
        String fromStatus = opSysUser.getStatus();
        String toStatus = "1";
        if("1".equals(fromStatus)){
            toStatus = "0";
        }
        SysUser opUser = new SysUser();
        opUser.setUserId(opUserId);
        opUser.setStatus(toStatus);
        updataUserInfo(opUser);
    }

    public void updataUserInfo(SysUser opUser) throws Exception {
        String opUserId = opUser.getUserId();
        if(StringUtil.isNull(opUserId)){
            throw new Exception("参数异常~");
        }
        Integer count = 1;
        String baseSql = "update sys_user set user_id = "+opUserId+"";

        if(!StringUtil.isNull(opUser.getUserEmail())){
            baseSql += ",user_email = '"+opUser.getUserEmail()+"'";
            count++;
        }

        if(!StringUtil.isNull(opUser.getUserPass())){
            baseSql += ",user_pass = '"+opUser.getUserPass()+"'";
            count++;
        }

        if(!StringUtil.isNull(opUser.getUserName())){
            baseSql += ",user_name = '"+opUser.getUserName()+"'";
            count++;
        }

        if(!StringUtil.isNull(opUser.getStatus())){
            baseSql += ",status = '"+opUser.getStatus()+"'";
            count++;
        }
        if(!StringUtil.isNull(opUser.getUserPhone())){
            baseSql += ",user_phone = '"+opUser.getUserPhone()+"'";
            count++;
        }

        if(!StringUtil.isNull(opUser.getUserImage())){
            baseSql += ",user_image = '"+opUser.getUserImage()+"'";
            count++;
        }

        if(!StringUtil.isNull(opUser.getUserIsAdmin())){
            baseSql += ",user_is_admin = '"+opUser.getUserIsAdmin()+"'";
            count++;
        }

        baseSql += " where user_id = "+opUserId+"";

        Connection con = MySqlUtil.getCon();
        PreparedStatement pre = con.prepareStatement(baseSql);
        pre.executeUpdate();
    }

    public void register(PageData pageData) throws Exception {
        SysUser newUser = getUserByParams(pageData);
        newUser.setUserPass(getUserPass(newUser.getUserPass()));
        if(getUserByUserPhone(newUser.getUserPhone())!=null){
            throw new Exception("该手机号已经被注册了~");
        }
        if(getUserByUserEmail(newUser.getUserEmail())!=null){
            throw new Exception("该邮箱已经被注册了~");
        }
        if(getUserByUserName(newUser.getUserName())!=null){
            throw new Exception("该用户名已经被注册了~");
        }
        //保存到数据库
        try {
            Connection con = MySqlUtil.getCon();
            String sql = "insert into sys_user (user_name,user_pass,user_email,user_phone,status,user_is_admin) values (?,?,?,?,1,0)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,newUser.getUserName());
            preparedStatement.setString(2,newUser.getUserPass());
            preparedStatement.setString(3,newUser.getUserEmail());
            preparedStatement.setString(4,newUser.getUserPhone());
            preparedStatement.executeUpdate();
        }catch (Exception  e){
            e.printStackTrace();
            throw new Exception("服务器异常，请联系系统管理员~");
        }
    }

    public PageData list(PageData params) throws Exception {
        String key = params.getString("key");
        String baseSql = "select user_id,user_name,user_email," +
                "user_phone,status,user_image,user_is_admin from sys_user where (1=1)";
        if(!StringUtil.isNull(key)){
            baseSql += "and user_name LIKE CONCAT(CONCAT('%','"+key+"'),'%')";
            baseSql += "and user_email LIKE CONCAT(CONCAT('%','"+key+"'),'%')";
            baseSql += "and user_phone LIKE CONCAT(CONCAT('%','"+key+"'),'%')";
        }
        MySqlPageUtlil pageUtlil = new MySqlPageUtlil(baseSql,params);
        Map<String,Object> page = new PageData();
        page.put("total",pageUtlil.getTotal());
        page.put("pageSize",pageUtlil.getPageSize());
        page.put("pageIndex",pageUtlil.getPageIndex());
        PageData res = new PageData();
        res.put("page",page);
        res.put("list",pageUtlil.getArray(SysUser.class));
        return res;
    }

    private SysUser getUserByUserId(String userId) throws Exception {
        if(StringUtil.isNull(userId)){
            return null;
        }
        try {
            Connection connection = MySqlUtil.getCon();
            String sql = "select * from sys_user where user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userId);
            ResultSet  resultSet = preparedStatement.executeQuery();
            List<SysUser> sysUserList = ResultSetUtil.getArray(resultSet,SysUser.class);
            if(sysUserList.size()>0){
                return sysUserList.get(0);
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private SysUser getUserByUserPhone(String userPhone) throws Exception {
        if(StringUtil.isNull(userPhone)){
            return null;
        }
        try {
            Connection connection = MySqlUtil.getCon();
            String sql = "select * from sys_user where user_phone = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userPhone);
            ResultSet  resultSet = preparedStatement.executeQuery();
            List<SysUser> sysUserList = ResultSetUtil.getArray(resultSet,SysUser.class);
            if(sysUserList.size()>0){
                return sysUserList.get(0);
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private SysUser getUserByUserEmail(String userEmail) throws Exception {
        if(StringUtil.isNull(userEmail)){
            return null;
        }
        try {
            Connection connection = MySqlUtil.getCon();
            String sql = "select * from sys_user where user_email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userEmail);
            ResultSet  resultSet = preparedStatement.executeQuery();
            List<SysUser> sysUserList = ResultSetUtil.getArray(resultSet,SysUser.class);
            if(sysUserList.size()>0){
                return sysUserList.get(0);
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private SysUser getUserByUserName(String userName) throws Exception {
        if(StringUtil.isNull(userName)){
            return null;
        }
        try {
            Connection connection = MySqlUtil.getCon();
            String sql = "select * from sys_user where user_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userName);
            ResultSet  resultSet = preparedStatement.executeQuery();
            List<SysUser> sysUserList = ResultSetUtil.getArray(resultSet,SysUser.class);
            if(sysUserList.size()>0){
                return sysUserList.get(0);
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private SysUser getUserByParams(PageData params) throws Exception {
        if(params!=null){
            //判断数据是否为空
            String userEmail = params.getString("userEmail");
            if(StringUtil.isNull(userEmail)){
                throw new Exception("参数异常~");
            }
            String userName = params.getString("userName");
            if(StringUtil.isNull(userName)){
                throw new Exception("参数异常~");
            }
            String userPhone = params.getString("userPhone");
            if(StringUtil.isNull(userPhone)){
                throw new Exception("参数异常~");
            }
            String userPass = params.getString("userPass");
            if(StringUtil.isNull(userPass)){
                throw new Exception("参数异常~");
            }
            SysUser sysUser = new SysUser();
            sysUser.setUserEmail(userEmail);
            sysUser.setUserName(userName);
            sysUser.setUserPass(userPass);
            sysUser.setUserPhone(userPhone);
            return sysUser;
        }else {
            throw new Exception("参数异常~");
        }
    }

    private void saveUserLoginStatus(HttpServletRequest request,SysUser sysUser){
        request.setAttribute("user",sysUser);
    }

    private SysUser loginByPhoneAndPass(String phone,String pass) throws Exception {
        String[] params = {phone,pass};
        String sql = "select * from sys_user where user_phone = ? and user_pass = ? limit 1";
        return login(sql,params);
    }

    private SysUser loginByEmailAndPass(String email,String pass) throws Exception {
        String[] params = {email,pass};
        String sql = "select * from sys_user where user_email = ? and user_pass = ? limit 1";
        return login(sql,params);
    }

    private SysUser loginByUserIdAndPass(String userId,String pass) throws Exception {
        String[] params = {userId,pass};
        String sql = "select * from sys_user where user_id = ? and user_pass = ? limit 1";
        return login(sql,params);
    }

    private SysUser login(String sql,String[] params) throws Exception {
        Connection con = MySqlUtil.getCon();
        PreparedStatement  preparedStatement = con.prepareStatement(sql);
        for (int i = 1; i <= params.length; i++) {
            preparedStatement.setString(i,params[(i-1)]);
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        List<SysUser> sysUserList = ResultSetUtil.getArray(resultSet,SysUser.class);
        if(sysUserList.size()>0){
            return sysUserList.get(0);
        }
        return null;
    }

    private static String getUserPass(String str){
        return Md5.string2MD5(Md5.string2MD5(str)+"1912114103");
    }

    @Override
    public boolean addAddr(SysUserAddr addr) throws Exception {
        return false;
    }

    @Override
    public boolean deleteAddr(SysUserAddr addr) throws Exception {
        return false;
    }

    @Override
    public boolean updateAddr(SysUserAddr addr) throws Exception {
        return false;
    }

    @Override
    public SysUserAddr selectAddr(int addrId) throws Exception {
        return null;
    }

    @Override
    public List selectAllAddr(int addrId) throws Exception {
        return null;
    }
}
