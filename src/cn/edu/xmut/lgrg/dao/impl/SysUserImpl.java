package cn.edu.xmut.lgrg.dao.impl;

import cn.edu.xmut.lgrg.annotation.ZnService;
import cn.edu.xmut.lgrg.dao.SysUserDao;
import cn.edu.xmut.lgrg.entity.SysUser;
import cn.edu.xmut.lgrg.util.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

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



        return sysUser;
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
}
