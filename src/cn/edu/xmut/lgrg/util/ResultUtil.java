package cn.edu.xmut.lgrg.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ResultUtil {
    public static ResultData success(Object object) {
        ResultData result = new ResultData();
        result.setCode(1);
        result.setErrmsg("请求成功!");
        result.setData(object);
        return result;
    }

    public static ResultData success() {
        return success(new String[]{});
    }

    public static ResultData error(String msg) {
        ResultData result = new ResultData();
        result.setCode(0);
        result.setErrmsg(msg == null ? "服务器异常！" : msg);
        return result;
    }

    public static ResultData error() {
        return error("服务器异常！");
    }
    public static void out(ServletResponse hresponse, ResultData res){
        try {
            HttpServletResponse response= (HttpServletResponse) hresponse;
            response.setHeader("Content-type", "application/json;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.println(JSON.toJSONString(res));
            out.flush();
        } catch (Exception e) {
            System.err.println("KickoutSessionFilter.class 输出JSON异常，可以忽略。");
        }
    }
    public static void outSuccess(ServletResponse hresponse, Object ob){
        out(hresponse,success(ob));
    }
    public static void outSuccess(ServletResponse hresponse){
        out(hresponse,success());
    }
    public static void outError(ServletResponse hresponse,String errmsg){
        out(hresponse,error(errmsg));
    }
    public static void outError(ServletResponse hresponse){
        out(hresponse,error("错误请求~"));
    }

    public static void out(ServletResponse hresponse){
        outError(hresponse,"请登陆!");
    }
}
