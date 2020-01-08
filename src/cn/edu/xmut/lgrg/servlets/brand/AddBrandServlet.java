package cn.edu.xmut.lgrg.servlets.brand;

import cn.edu.xmut.lgrg.entity.Brand;
import cn.edu.xmut.lgrg.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/brand/add.do")
public class AddBrandServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("brandName");
        try {
            if(StringUtil.isNull(name)){
                throw new Exception("参数不完整~");
            }
            Connection con = MySqlUtil.getCon();
            String sql = "INSERT INTO brand(`name`,create_time,status) values(?,now(),1)";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, name);
            int rs = pstm.executeUpdate();
            ResultUtil.outSuccess(response, "插入成功！");
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtil.outError(response, e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        ResultUtil.outError(response, "错误请求~");
    }
}
