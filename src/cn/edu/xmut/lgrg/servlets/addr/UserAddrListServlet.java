package cn.edu.xmut.lgrg.servlets.addr;

import cn.edu.xmut.lgrg.dao.impl.SysUserAddrlmpl;
import cn.edu.xmut.lgrg.entity.SysUserAddr;
import cn.edu.xmut.lgrg.util.BeanUtil;
import cn.edu.xmut.lgrg.util.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Auther: ZiNan
 * @Date: 2020/1/6 21:25
 * @Description:
 */
@WebServlet("/user/ua/list.do")
public class UserAddrListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SysUserAddrlmpl addrService = BeanUtil.getInstance(SysUserAddrlmpl.class);
        try {
            List<SysUserAddr> list = addrService.selectAllAddr(req);
            ResultUtil.outSuccess(resp,list);
        }catch (Exception e){
            e.printStackTrace();
            ResultUtil.outError(resp,e.getMessage());
        }
    }
}
