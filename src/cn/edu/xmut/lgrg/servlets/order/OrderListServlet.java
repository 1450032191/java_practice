package cn.edu.xmut.lgrg.servlets.order;

import cn.edu.xmut.lgrg.dao.impl.OrderImpl;
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
 * @Date: 2020/1/8 21:00
 * @Description:
 */
@WebServlet("/admin/order/list.do")
public class OrderListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            OrderImpl orderService = BeanUtil.getInstance(OrderImpl.class);
            PageData res = orderService.getOrderPageList(req);
            ResultUtil.outSuccess(resp,res);
        }catch (Exception e){
            e.printStackTrace();
            ResultUtil.outError(resp,e.getMessage());
        }
    }
}
