package cn.edu.xmut.lgrg.servlets.order;

import cn.edu.xmut.lgrg.dao.impl.OrderImpl;
import cn.edu.xmut.lgrg.util.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author azx
 * @create 2020-01-06-17:08
 */
@WebServlet("/order/pay.do")
public class OrderPaymentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("utf-8");
         response.setContentType("text/html;charset=utf-8");

      String order_id = request.getParameter("order_id");
//         String order_id="1";//暂定订单号



        try {
            //修改订单的状态并返回修改的结果
            boolean payment = new OrderImpl().changePaymentOrderStatue(order_id);

            ResultUtil.outSuccess(response,payment);
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtil.outError(response,"订单异常");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
