package cn.edu.xmut.lgrg.servlets.user;

import cn.edu.xmut.lgrg.dao.impl.SysUserImpl;
import cn.edu.xmut.lgrg.entity.PageData;
import cn.edu.xmut.lgrg.util.BeanUtil;
import cn.edu.xmut.lgrg.util.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/register.do")
public class UserRegisterServlet extends HttpServlet {
    SysUserImpl sysUserImpl = BeanUtil.getInstance(SysUserImpl.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            sysUserImpl.register(new PageData(request));
            ResultUtil.outSuccess(response,"注册成功~");
        }catch (Exception e){
            ResultUtil.outError(response,e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultUtil.outError(response);
    }
}
