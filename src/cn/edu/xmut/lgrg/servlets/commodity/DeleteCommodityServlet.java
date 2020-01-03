package cn.edu.xmut.lgrg.servlets.commodity;

import cn.edu.xmut.lgrg.dao.impl.SysCommdityImpl;
import cn.edu.xmut.lgrg.util.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author azx
 * @create 2020-01-02-9:08
 */
@WebServlet("/commodity/deleteCommodityServlet")
public class DeleteCommodityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");

        try {
            //清空该用户的所有购物车商品
            boolean result = new SysCommdityImpl().deleteAllCommodity(request);

            if (result){
                ResultUtil.outSuccess(response,result);
            }else{
                ResultUtil.outError(response,String.valueOf(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {

    }
}
