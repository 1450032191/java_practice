package cn.edu.xmut.lgrg.servlets.brand;

import cn.edu.xmut.lgrg.entity.Brand;
import cn.edu.xmut.lgrg.util.MySqlUtil;
import cn.edu.xmut.lgrg.util.ResultSetUtil;
import cn.edu.xmut.lgrg.util.ResultUtil;

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

@WebServlet("/brand/detail.do")
public class DetailBrandServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        List<Map<Integer, String>> res = new ArrayList<>();
        try {
            Connection con = MySqlUtil.getCon();
            String sql = "select * from brand";
            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet resultSet = pre.executeQuery();
            List<Brand> brandList = ResultSetUtil.getArray(resultSet, Brand.class);
            ResultUtil.outSuccess(resp, brandList);
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtil.outError(resp, e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        ResultUtil.outError(resp, "错误请求~");
    }
}
