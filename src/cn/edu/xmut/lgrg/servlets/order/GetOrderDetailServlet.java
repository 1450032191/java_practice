package cn.edu.xmut.lgrg.servlets.order;

import cn.edu.xmut.lgrg.dao.impl.OrderImpl;
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
 * @Date: 2020/1/9 22:34
 * @Description:
 */
@WebServlet("/order/detail.do")
public class GetOrderDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            OrderImpl orderService = BeanUtil.getInstance(OrderImpl.class);
            ResultUtil.outSuccess(resp,orderService.getOrderDetail(req));
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtil.outError(resp,"该用户没有订单");
        }
    }
}
