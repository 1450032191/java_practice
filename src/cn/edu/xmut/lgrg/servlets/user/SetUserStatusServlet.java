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

@WebServlet("/user/setUserStatus.do")
public class SetUserStatusServlet extends HttpServlet {
    SysUserImpl sysUserImpl = BeanUtil.getInstance(SysUserImpl.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            sysUserImpl.setUserStatus(new PageData(request));
            ResultUtil.outSuccess(response);
        }catch (Exception e){
            e.printStackTrace();
            ResultUtil.outError(response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultUtil.outError(response);
    }
}
