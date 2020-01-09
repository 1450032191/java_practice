package cn.edu.xmut.lgrg.servlets.notice;

import cn.edu.xmut.lgrg.dao.impl.SysNoticeImpl;
import cn.edu.xmut.lgrg.entity.SysNotice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/NoticeServlet")
public class NoticeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        SysNoticeImpl sysNotice = new SysNoticeImpl();
        String[] bb = new String[]{request.getParameter("user"), request.getParameter("headline"), request.getParameter("content")};
        try {
            sysNotice.addNotice(bb, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String[]> list = new ArrayList<>();
        try {
            list = sysNotice.selectNotice(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("list", list);

        request.getRequestDispatcher("notice/notice.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
