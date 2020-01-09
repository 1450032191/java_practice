package cn.edu.xmut.lgrg.filter;

import cn.edu.xmut.lgrg.entity.SysUser;
import cn.edu.xmut.lgrg.util.UserUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: ZiNan
 * @Date: 2020/1/8 22:12
 * @Description:
 */
public class LoginFilter implements Filter {
    List<String> loginList;
    List<String> adminList;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        loginList = new ArrayList<>();
        loginList.add("view/client/adress.jsp");
        loginList.add("view/client/car.jsp");
        loginList.add("view/client/orders.jsp");

        adminList = new ArrayList<>();
        adminList.add("view/admin");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        SysUser user = UserUtil.getSysUser(request);
        //获取访问地址
        String url = request.getRequestURI();

    }

    @Override
    public void destroy() {

    }
}
