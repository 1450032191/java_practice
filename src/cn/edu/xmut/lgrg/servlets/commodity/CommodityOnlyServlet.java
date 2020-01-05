package cn.edu.xmut.lgrg.servlets.commodity;

import cn.edu.xmut.lgrg.dao.impl.SysCommodityImpl;
import cn.edu.xmut.lgrg.entity.PageData;
import cn.edu.xmut.lgrg.entity.SysCommodity;
import cn.edu.xmut.lgrg.util.BeanUtil;
import cn.edu.xmut.lgrg.util.ResultUtil;
import cn.edu.xmut.lgrg.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: pigNan
 * @Date: 2020/1/5 22:13
 * @Description:
 */
@WebServlet("/item.do")
public class CommodityOnlyServlet extends HttpServlet {
    SysCommodityImpl sysCommodity = BeanUtil.getInstance(SysCommodityImpl.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PageData params = new PageData(req);
            String comId = params.getString("comId");
            if(StringUtil.isNull(comId)){
                resp.sendRedirect("/");
            }else{
                SysCommodity commodity = sysCommodity.selectComm(Integer.parseInt(comId));
                ResultUtil.outSuccess(resp,commodity);
            }
        }catch (Exception e){
            e.printStackTrace();
            ResultUtil.outError(resp,e.getMessage());
        }
    }
}
