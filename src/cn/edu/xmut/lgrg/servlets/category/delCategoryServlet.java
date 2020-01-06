package cn.edu.xmut.lgrg.servlets.category;

import cn.edu.xmut.lgrg.dao.impl.CommodityCategoryDaoImpl;
import cn.edu.xmut.lgrg.entity.PageData;
import cn.edu.xmut.lgrg.util.BeanUtil;
import cn.edu.xmut.lgrg.util.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: ZiNan
 * @Date: 2020/1/6 18:38
 * @Description:
 */
@WebServlet("/category/del.do")
public class delCategoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CommodityCategoryDaoImpl categoryService = BeanUtil.getInstance(CommodityCategoryDaoImpl.class);
            categoryService.del(new PageData(req));
            ResultUtil.outSuccess(resp);
        }catch (Exception e){
            e.printStackTrace();
            ResultUtil.outError(resp,e.getMessage());
        }
    }
}
