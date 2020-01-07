package cn.edu.xmut.lgrg.servlets.brand;

import cn.edu.xmut.lgrg.dao.impl.SysBrandDaoIml;
import cn.edu.xmut.lgrg.entity.Brand;
import cn.edu.xmut.lgrg.entity.PageData;
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
 * @Date: 2020/1/6 20:15
 * @Description:
 */
@WebServlet("/brand/list.do")
public class BrandListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            SysBrandDaoIml brandService = BeanUtil.getInstance(SysBrandDaoIml.class);
            List<Brand> list = brandService.list();
            ResultUtil.outSuccess(resp,list);
        }catch (Exception e){
            e.printStackTrace();
            ResultUtil.outError(resp,e.getMessage());
        }
    }
}
