package cn.edu.xmut.lgrg.servlets.commodity;

import cn.edu.xmut.lgrg.dao.impl.SysCommodityImpl;
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
 * @Date: 2020/1/5 21:30
 * @Description:
 */
@WebServlet("/commodity/list.do")
public class CommodityListServlet extends HttpServlet {
    SysCommodityImpl commodity = BeanUtil.getInstance(SysCommodityImpl.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ResultUtil.outSuccess(response,commodity.list(new PageData(request)));
        }catch (Exception e){
            e.printStackTrace();
            ResultUtil.outError(response);
        }
    }
}
