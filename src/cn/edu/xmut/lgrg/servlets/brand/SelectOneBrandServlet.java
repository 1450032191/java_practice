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
import java.util.List;
import java.util.Map;

@WebServlet("/brand/selectone.do")
public class SelectOneBrandServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Map<Integer, String>> res = new ArrayList<>();
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            Connection con = MySqlUtil.getCon();
            String sql = "select * from brand where id = ?";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet resultSet = pre.executeQuery();
            List<Brand> brandList = ResultSetUtil.getArray(resultSet, Brand.class);
            ResultUtil.outSuccess(resp, brandList);
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtil.outError(resp, e.getMessage());
        }
    }
}
