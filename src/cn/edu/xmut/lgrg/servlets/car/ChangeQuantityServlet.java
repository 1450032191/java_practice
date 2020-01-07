package cn.edu.xmut.lgrg.servlets.car;

import cn.edu.xmut.lgrg.dao.impl.SysCarImpl;
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
 * @author azx
 * @create 2020-01-03-16:25
 */
@WebServlet("/cart/set.do")
public class ChangeQuantityServlet extends HttpServlet {
    SysCarImpl carService = BeanUtil.getInstance(SysCarImpl.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //调用更改商品的数量的方法
        try {
            //获取商品id和 商品数量
            PageData params = new PageData(request);
            int comId=Integer.parseInt(params.getString("sku"));
            int quantity=Integer.parseInt(params.getString("skuCount"));
            boolean result = carService.changeQuantity(request, comId, quantity);
            if (result){
                ResultUtil.outSuccess(response,result);
            }else{
                ResultUtil.outError(response,String.valueOf(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtil.outError(response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
