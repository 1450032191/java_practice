package cn.edu.xmut.lgrg.servlets.car;

import cn.edu.xmut.lgrg.dao.impl.SysCarImpl;
import cn.edu.xmut.lgrg.entity.PageData;
import cn.edu.xmut.lgrg.entity.SysCar;
import cn.edu.xmut.lgrg.util.BeanUtil;
import cn.edu.xmut.lgrg.util.ResultUtil;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Auther: ZiNan
 * @Date: 2020/1/7 21:50
 * @Description:
 */
@WebServlet("/cart/delArr.do")
public class DelArrServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PageData params = new PageData(req);
            String skuList = params.getString("skuList");
            List<String> list = JSON.parseArray(skuList,String.class);
            SysCarImpl carService = BeanUtil.getInstance(SysCarImpl.class);
            for (int i = 0; i < list.size(); i++) {
                String sku = list.get(i);
                carService.changeQuantity(req,Integer.valueOf(sku),0);
            }
            ResultUtil.outSuccess(resp);
        }catch (Exception e){
            e.printStackTrace();
            ResultUtil.outError(resp,e.getMessage());
        }
    }
}
