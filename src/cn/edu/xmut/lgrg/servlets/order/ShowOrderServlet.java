package cn.edu.xmut.lgrg.servlets.order;

import cn.edu.xmut.lgrg.dao.impl.OrderImpl;
import cn.edu.xmut.lgrg.entity.SysOrder;
import cn.edu.xmut.lgrg.util.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author azx
 * @create 2020-01-06-10:08
 */
@WebServlet("/order/list.do")
public class ShowOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           request.setCharacterEncoding("utf-8");
           response.setContentType("text/html;charset=utf-8");
           try {
               List<SysOrder> orderInfo = new OrderImpl().getOrderByUserId(request);
               ResultUtil.outSuccess(response,orderInfo);
           } catch (Exception e) {
               e.printStackTrace();
               ResultUtil.outError(response,"该用户没有订单");
           }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
