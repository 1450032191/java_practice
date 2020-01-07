package cn.edu.xmut.lgrg.servlets.commodity;

import cn.edu.xmut.lgrg.dao.impl.SysCommodityImpl;
import cn.edu.xmut.lgrg.entity.PageData;
import cn.edu.xmut.lgrg.entity.SysCommodity;
import cn.edu.xmut.lgrg.util.BeanUtil;
import cn.edu.xmut.lgrg.util.ResultUtil;
import cn.edu.xmut.lgrg.util.StringUtil;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: ZiNan
 * @Date: 2020/1/5 22:56
 * @Description:
 */
@WebServlet("/commodity/add.do")
public class AddCommodityServlet extends HttpServlet {
    SysCommodityImpl commodityService = BeanUtil.getInstance(SysCommodityImpl.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PageData params = new PageData(req);
            String str = JSON.toJSONString(params);
            SysCommodity commodity = JSON.parseObject(str,SysCommodity.class);
            commodityService.addComm(commodity);
            ResultUtil.outSuccess(resp);
        }catch (Exception e){
            e.printStackTrace();
            ResultUtil.outError(resp,e.getMessage());
        }
    }
}
