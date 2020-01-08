package cn.edu.xmut.lgrg.servlets.view;

import cn.edu.xmut.lgrg.dao.impl.CommodityCategoryDaoImpl;
import cn.edu.xmut.lgrg.dao.impl.SysCommodityImpl;
import cn.edu.xmut.lgrg.entity.CommodityCategory;
import cn.edu.xmut.lgrg.entity.PageData;
import cn.edu.xmut.lgrg.entity.SysCommodity;
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
 * @Date: 2020/1/5 21:14
 * @Description:
 */
@WebServlet("/index.do")
public class IndexViewServlet extends HttpServlet {
    SysCommodityImpl commodityService = BeanUtil.getInstance(SysCommodityImpl.class);
    CommodityCategoryDaoImpl categoryService = BeanUtil.getInstance(CommodityCategoryDaoImpl.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<SysCommodity> miaosha = commodityService.getComList(4);
            List<SysCommodity> skuList = commodityService.getComList(20);
            List<SysCommodity> paiList = commodityService.getComList(6);
            List<CommodityCategory> category = categoryService.formatList();
            PageData res = new PageData();
            res.put("miaosha",miaosha);
            res.put("paiList",paiList);
            res.put("skuList",skuList);
            res.put("category",category);
            ResultUtil.outSuccess(resp,res);
        }catch (Exception e){
            e.printStackTrace();
            ResultUtil.outError(resp,e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
