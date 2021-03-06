package cn.edu.xmut.lgrg.servlets.user;

import cn.edu.xmut.lgrg.dao.impl.SysUserImpl;
import cn.edu.xmut.lgrg.entity.SysUser;
import cn.edu.xmut.lgrg.util.BeanUtil;
import cn.edu.xmut.lgrg.util.MySqlUtil;
import cn.edu.xmut.lgrg.util.ResultUtil;
import cn.edu.xmut.lgrg.util.UserUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/user/login.do")
public class UserLoginServlet extends HttpServlet {
    SysUserImpl sysUserImpl = BeanUtil.getInstance(SysUserImpl.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Map<String,String>> res = new ArrayList<>();
        String userId = UserUtil.getUserId(request);
        String userName = request.getParameter("name");
        String userPass = request.getParameter("pass");
        String checkCode = "";
        try {
            SysUser sysUser = sysUserImpl.login(request,userName,userPass,checkCode);
            ResultUtil.outSuccess(response,sysUser);
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtil.outError(response,e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultUtil.outError(response,"错误请求~");
//        doPost(request,response);
    }

}
