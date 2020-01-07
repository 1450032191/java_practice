package cn.edu.xmut.lgrg.servlets.order;

import cn.edu.xmut.lgrg.dao.impl.OrderImpl;
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
@WebServlet("/order/showOrderServlet")
public class ShowOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           request.setCharacterEncoding("utf-8");
           response.setContentType("text/html;charset=utf-8");

           try {
               List<Map<String, String>> orderInfo = new OrderImpl().getOrderByUserId(request);

               if (orderInfo!=null && orderInfo.size()>0){
                   ResultUtil.outSuccess(response,orderInfo);
               }else{
                   ResultUtil.outError(response,"该用户没有订单");
               }
           } catch (Exception e) {
               e.printStackTrace();
               ResultUtil.outError(response,"该用户没有订单");
           }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
