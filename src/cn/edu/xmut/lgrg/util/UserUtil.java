package cn.edu.xmut.lgrg.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: ZiNan
 * @Date: 2019/12/2 19:57
 * @Description:
 */
public class UserUtil {
    public static String getUserId(HttpServletRequest request){
        return "1";
    }

    public static String getImgBaseUrl(){
        return "";
    }

    public static String getUserName(HttpServletRequest request){
        return getUserItem(request,"userName");
    }

    public static String getUserEmail(HttpServletRequest request){
        return getUserItem(request,"userEmail");
    }

    public static String getUserPhone(HttpServletRequest request){
        return getUserItem(request,"userPhone");
    }

    private static String getUserItem(HttpServletRequest request,String itemKey){
        return "";
    }

    /**
     * 用户身份证号码的打码隐藏加星号加*
     * <p>18位和非18位身份证处理均可成功处理</p>
     * <p>参数异常直接返回null</p>
     *
     * @param idCardNum 身份证号码
     * @param front     需要显示前几位
     * @param end       需要显示末几位
     * @return 处理完成的身份证
     */
    public static String idMask(String idCardNum, int front, int end) {
        //身份证不能为空
        if (idCardNum==null || "".equals(idCardNum)) {
            return null;
        }
        //需要截取的长度不能大于身份证号长度
        if ((front + end) > idCardNum.length()) {
            return null;
        }
        //需要截取的不能小于0
        if (front < 0 || end < 0) {
            return null;
        }
        //计算*的数量
        int asteriskCount = idCardNum.length() - (front + end);
        StringBuffer asteriskStr = new StringBuffer();
        for (int i = 0; i < asteriskCount; i++) {
            asteriskStr.append("*");
        }
        String regex = "(\\w{" + String.valueOf(front) + "})(\\w+)(\\w{" + String.valueOf(end) + "})";
        return idCardNum.replaceAll(regex, "$1" + asteriskStr + "$3");
    }
}
