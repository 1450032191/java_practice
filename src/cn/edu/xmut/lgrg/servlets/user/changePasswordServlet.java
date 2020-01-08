package cn.edu.xmut.lgrg.servlets.user;

import cn.edu.xmut.lgrg.dao.impl.SysUserImpl;
import cn.edu.xmut.lgrg.util.MySqlUtil;
import cn.edu.xmut.lgrg.util.ResultUtil;
import cn.edu.xmut.lgrg.util.UserUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author azx
 * @create 2020-01-07-20:38
 */
@WebServlet("/user/changePasswordServlet")
public class changePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charser=utf-8");

        //获取新密码、旧密码
        String user_pass = request.getParameter("oldpass");
        String newpass = request.getParameter("newpass");
        String renewpass = request.getParameter("renewpass");


        if (newpass.equals(renewpass)) {//先判断新密码是否相同，不同则先不校验用户密码

            //加密从表单获取的旧密码将与数据库中密码对比
            String userPass = SysUserImpl.getUserPass(user_pass);
            //获取用户id
            String userId = UserUtil.getUserId(request);
            //获取数据库中的密码
            String sql = "select user_pass from sys_user where user_id=" + userId;
            Connection con = null;
            ResultSet rs = null;
            String user_pass1 = null;
            try {
                con = MySqlUtil.getCon();
                PreparedStatement ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                if (rs.next()) {
                    user_pass1 = rs.getString("user_pass");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            //比较输入的旧密码和数据库获取的密码
            if (userPass.equals(user_pass1)) { //相同则将新密码加密后保存到数据库
                //加密新密码
                String encryptionPass = SysUserImpl.getUserPass(newpass);

                String updatesql = "update sys_user set user_pass=" + encryptionPass + " where user_id=" + userId;

                //更新密码
                boolean result = false;
                try {
                    result = SysUserImpl.updatePass(updatesql);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (result) {
                    ResultUtil.outSuccess(response, String.valueOf(result));
                } else {
                    ResultUtil.outError(response, String.valueOf(result));
                }

                request.setAttribute("msg", "更新成功");
                request.getRequestDispatcher("/view/admin/changePass.jsp").forward(request, response);

            } else {
                request.setAttribute("msg", "旧密码错误");
                request.getRequestDispatcher("/view/admin/changePass.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("msg", "两次新密码不同");
            request.getRequestDispatcher("/view/admin/changePass.jsp").forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
