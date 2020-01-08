package cn.edu.xmut.lgrg.servlets.order;

import cn.edu.xmut.lgrg.dao.impl.OrderImpl;
import cn.edu.xmut.lgrg.dao.impl.SysCarImpl;
import cn.edu.xmut.lgrg.entity.SysCar;
import cn.edu.xmut.lgrg.entity.SysOrder;
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
 * @Date: 2020/1/7 22:19
 * @Description:
 */
@WebServlet("/order/add.do")
public class GenOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            OrderImpl orderService = BeanUtil.getInstance(OrderImpl.class);
            orderService.generateOrder(req);
            ResultUtil.outSuccess(resp);
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtil.outError(resp,"该用户没有订单");
        }
    }
}
