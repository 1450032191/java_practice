package cn.edu.xmut.lgrg.servlets.notic;

import cn.edu.xmut.lgrg.dao.impl.SysNoticImpl;
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
 * @Auther: ZiNan
 * @Date: 2020/1/9 10:37
 * @Description:
 */

@WebServlet("/notic/del.do")
public class NoticDelServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SysNoticImpl noticService = BeanUtil.getInstance(SysNoticImpl.class);
        try {
            PageData params = new PageData(req);
            String id = params.getString("noticId");
            if("0".equals(id)){
                noticService.delAll(req);
            }else {
                noticService.del(req);
            }
            ResultUtil.outSuccess(resp);
        }catch (Exception e){
            e.printStackTrace();
            ResultUtil.outError(resp,e.getMessage());
        }
    }
}
