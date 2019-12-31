package cn.edu.xmut.lgrg.servlets;

import cn.edu.xmut.lgrg.util.CodeUtil;
import cn.edu.xmut.lgrg.util.ResultUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/util/valicode.do")
public class ValicodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultUtil.outError(response,"错误请求~");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //利用图片工具生成图片
        //将图片输出给浏览器
        BufferedImage image = CodeUtil.generateCodeAndPic(request);
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }
}
