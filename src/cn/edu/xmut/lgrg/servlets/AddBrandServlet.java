package cn.edu.xmut.lgrg.servlets;

import cn.edu.xmut.lgrg.entity.Brand;
import cn.edu.xmut.lgrg.util.MySqlUtil;
import cn.edu.xmut.lgrg.util.ResultSetUtil;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/add")
public class AddBrandServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Map<String, String>> res = new ArrayList<>();
        String userId = UserUtil.getUserId(request);
        try {
            Connection con = MySqlUtil.getCon();
            String sql = "select * from sys_order";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Brand> brandList = ResultSetUtil.getArray(resultSet,Brand.class);
            ResultUtil.outSuccess(response, brandList);
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtil.outError(response, e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultUtil.outError(response, "错误请求~");
    }
}
