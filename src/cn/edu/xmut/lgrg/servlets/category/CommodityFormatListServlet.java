package cn.edu.xmut.lgrg.servlets.category;

import cn.edu.xmut.lgrg.dao.impl.CommodityCategoryDaoImpl;
import cn.edu.xmut.lgrg.entity.CommodityCategory;
import cn.edu.xmut.lgrg.util.BeanUtil;
import cn.edu.xmut.lgrg.util.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Auther: ZiNan
 * @Date: 2020/1/6 16:04
 * @Description:
 */
@WebServlet("/commodity/format.do")
public class CommodityFormatListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommodityCategoryDaoImpl categoryService = BeanUtil.getInstance(CommodityCategoryDaoImpl.class);
        try {
            List<CommodityCategory> res = categoryService.formatList();
            ResultUtil.outSuccess(resp,res);
        }catch (Exception e){
            e.printStackTrace();
            ResultUtil.outError(resp,e.getMessage());
        }
    }
}
