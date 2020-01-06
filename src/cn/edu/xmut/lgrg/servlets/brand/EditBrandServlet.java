package cn.edu.xmut.lgrg.servlets.brand;

import cn.edu.xmut.lgrg.entity.PageData;
import cn.edu.xmut.lgrg.util.MySqlUtil;
import cn.edu.xmut.lgrg.util.ResultUtil;
import cn.edu.xmut.lgrg.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @Auther: ZiNan
 * @Date: 2020/1/6 21:02
 * @Description:
 */
@WebServlet("/brand/edit.do")
public class EditBrandServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PageData pageData = new PageData(req);
            String brandId = pageData.getString("brandId");
            String brandName = pageData.getString("brandName");
            if(StringUtil.isNull(brandId)||StringUtil.isNull(brandName)){
                throw new Exception("错误~");
            }
            String sql = "update brand set name = ? where id = ?";
            Connection connection = MySqlUtil.getCon();
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1,brandName);
            pre.setString(2,brandId);
            pre.executeUpdate();
            ResultUtil.outSuccess(resp);
        }catch (Exception e){
            e.printStackTrace();
            ResultUtil.outError(resp,e.getMessage());
        }
    }
}
