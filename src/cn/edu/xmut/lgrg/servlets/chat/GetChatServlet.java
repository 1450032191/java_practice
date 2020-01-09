package cn.edu.xmut.lgrg.servlets.chat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: ZiNan
 * @Date: 2020/1/9 17:30
 * @Description:
 */
@WebServlet("/chat/get.do")
public class GetChatServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
