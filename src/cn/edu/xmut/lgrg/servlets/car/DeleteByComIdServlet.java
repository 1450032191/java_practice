package cn.edu.xmut.lgrg.servlets.car;

import cn.edu.xmut.lgrg.dao.impl.SysCarImpl;
import cn.edu.xmut.lgrg.util.ResultUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author azx
 * @create 2020-01-03-9:46
 */
@WebServlet("/car/deleteByComIdServlet")
public class DeleteByComIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        request.setCharacterEncoding("utf-8");
        //暂时先固定商品id为1,后边动态获取商品id
        int com_id=1;

        try {
            //
            boolean result = new SysCarImpl().deleteById(request,com_id);

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
