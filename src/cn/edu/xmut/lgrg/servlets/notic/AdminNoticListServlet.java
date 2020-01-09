package cn.edu.xmut.lgrg.servlets.notic;

import cn.edu.xmut.lgrg.dao.impl.SysNoticImpl;
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
 * @Date: 2020/1/9 16:24
 * @Description:
 */
@WebServlet("/admin/notic/list.do")
public class AdminNoticListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SysNoticImpl noticService = BeanUtil.getInstance(SysNoticImpl.class);
        try {
            ResultUtil.outSuccess(resp,noticService.list(req));
        }catch (Exception e){
            e.printStackTrace();
            ResultUtil.outError(resp,e.getMessage());
        }
    }
}
