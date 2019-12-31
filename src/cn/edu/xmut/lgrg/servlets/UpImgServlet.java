package cn.edu.xmut.lgrg.servlets;

import cn.edu.xmut.lgrg.util.ResultUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/util/upImg.do")
public class UpImgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String relpath="upload";
        String path=request.getRealPath(relpath);
        String fn=null;
        try {
            //使用UUID给图片重命名，并去掉四个“-”
            DiskFileItemFactory factory=new DiskFileItemFactory();
            ServletFileUpload sfu=new ServletFileUpload(factory);
            sfu.setHeaderEncoding("UTF-8");  //处理中文问题
            sfu.setSizeMax(1024*1024);   //限制文件大小

            List<FileItem> fileItems= sfu.parseRequest(request);  //解码请求 得到所有表单元素
            for (FileItem fi : fileItems) {
                System.out.println("表单名字是："+fi.getFieldName());
                //有可能是 文件，也可能是普通文字
                if (fi.isFormField()) { //这个选项是 文字
                    System.out.println("表单值为："+fi.getString());
                }else{
                    // 是文件
                    //获取图片后缀名
                    String format=fi.getName().substring(fi.getName().indexOf("."), fi.getName().length());
                    //图片命名
                    fn=UUID.randomUUID().toString().replaceAll("-", "")+format;
                    System.out.println("文件名是："+fn);  //文件名
                    // fn 是可能是这样的 c:\abc\de\tt\fish.jpg
                    fi.write(new File(path,fn));
                    //图片保存成功放回数据
                    String baseUrl = relpath+"/"+fn;
                    String basePath = request.getScheme() + "://"
                            + request.getServerName() + ":" + request.getServerPort()
                            + request.getContextPath() + "/";
                    Map<String,String> res = new HashMap<>();
                    res.put("path",baseUrl);
                    res.put("url",basePath+"/"+baseUrl);
                    ResultUtil.outSuccess(response,res);
                    return;
                }
            }
            ResultUtil.outError(response,"没有找到文件~");
        }catch (Exception e){
            e.printStackTrace();
            ResultUtil.outError(response,"图片上传失败，请重新尝试~");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultUtil.outError(response,"错误的请求！");
    }
}
