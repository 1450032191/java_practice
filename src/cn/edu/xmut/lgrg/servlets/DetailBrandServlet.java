package cn.edu.xmut.lgrg.servlets;

import cn.edu.xmut.lgrg.util.MySqlUtil;
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
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Map<Integer, String> map = new HashMap<>();
                map.put(id, name);
                res.add(map);
            }
            ResultUtil.outSuccess(resp, res);
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
