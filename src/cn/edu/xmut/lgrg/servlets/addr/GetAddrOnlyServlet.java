package cn.edu.xmut.lgrg.servlets.addr;

import cn.edu.xmut.lgrg.dao.impl.SysUserAddrlmpl;
import cn.edu.xmut.lgrg.entity.PageData;
import cn.edu.xmut.lgrg.entity.SysUserAddr;
import cn.edu.xmut.lgrg.util.BeanUtil;
import cn.edu.xmut.lgrg.util.ResultUtil;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: ZiNan
 * @Date: 2020/1/6 22:03
 * @Description:
 */
@WebServlet("/user/ua/get.do")
public class GetAddrOnlyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            SysUserAddrlmpl addrService = BeanUtil.getInstance(SysUserAddrlmpl.class);
            PageData params = new PageData(req);
            String uaId = params.getString("uaId");
            SysUserAddr addr = addrService.selOnly(uaId);
            ResultUtil.outSuccess(resp,addr);
        }catch (Exception e){
            e.printStackTrace();
            ResultUtil.outError(resp,e.getMessage());
        }
    }
}
