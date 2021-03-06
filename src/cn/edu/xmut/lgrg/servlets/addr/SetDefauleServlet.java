package cn.edu.xmut.lgrg.servlets.addr;

import cn.edu.xmut.lgrg.dao.impl.SysUserAddrlmpl;
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
 * @Date: 2020/1/6 21:40
 * @Description:
 */
@WebServlet("/user/ua/serDefault.do")
public class SetDefauleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            SysUserAddrlmpl addrService = BeanUtil.getInstance(SysUserAddrlmpl.class);
            addrService.setDeatault(req,new PageData(req));
            ResultUtil.outSuccess(resp);
        }catch (Exception e){
            e.printStackTrace();
            ResultUtil.outError(resp,e.getMessage());
        }
    }
}
