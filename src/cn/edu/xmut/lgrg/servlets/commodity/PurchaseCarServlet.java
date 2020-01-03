package cn.edu.xmut.lgrg.servlets.commodity;

import cn.edu.xmut.lgrg.dao.impl.SysCommdityImpl;
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
 * @create 2019-12-31-11:20
 */
@WebServlet("/commodity/purchaseCarServlet")
public class PurchaseCarServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        try {
            //点击购物车展示所有商品
            List<Map<String, String>> allShoes = new SysCommdityImpl().getAllShoes(request);

            if(allShoes.size()==0){
                ResultUtil.outError(response,"商品为零");
            }else{
                ResultUtil.outSuccess(response,allShoes);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
