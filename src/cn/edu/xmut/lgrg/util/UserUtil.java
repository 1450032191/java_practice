package cn.edu.xmut.lgrg.util;

import cn.edu.xmut.lgrg.entity.SysUser;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: ZiNan
 * @Date: 2019/12/2 19:57
 * @Description:
 */
public class UserUtil {
    public static String getUserId(HttpServletRequest request) {
        SysUser sysUser = getSysUser(request);
        if(sysUser == null){
            return null;
        }
        return sysUser.getUserId();
//        return "1";
    }

    public static String getUserName(HttpServletRequest request) {
        SysUser sysUser = getSysUser(request);
        if (sysUser == null) {
            return null;
        }
        return sysUser.getUserName();
    }

    public static String getUserEmail(HttpServletRequest request) {
        SysUser sysUser = getSysUser(request);
        if (sysUser == null) {
            return null;
        }
        return sysUser.getUserEmail();
    }

    public static String getUserPhone(HttpServletRequest request) {
        SysUser sysUser = getSysUser(request);
        if (sysUser == null) {
            return null;
        }
        return sysUser.getUserPhone();
    }

    public static String getUserImage(HttpServletRequest request) {
        SysUser sysUser = getSysUser(request);
        if (sysUser == null) {
            return null;
        }
        return sysUser.getUserImage();
    }

    public static Boolean isAdmin(HttpServletRequest request){
        SysUser sysUser = getSysUser(request);
        if (sysUser == null) {
            return null;
        }
        return "1".equals(sysUser.getUserIsAdmin());
    }

    private static String getUserItem(HttpServletRequest request, String itemKey) {
        return "";
    }

    static SysUser sysUser = new SysUser();
    static {
        sysUser.setUserId("1");
        sysUser.setStatus("1");
        sysUser.setUserPhone("13055699880");
        sysUser.setUserEmail("1450032191@qq.com");
        sysUser.setUserImage("images/base/profile.jpg");
        sysUser.setUserName("测试用户");
        sysUser.setUserIsAdmin("1");
    }

    public static SysUser getSysUser(HttpServletRequest request) {
        //        开发环境下直接返回
        Object ob = request.getSession().getAttribute("user");
        if (ob != null && ob instanceof SysUser) {
            SysUser sysUser = (SysUser) ob;
            return sysUser;
        }

        //本地开发
//        return sysUser;
        return null;
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
        if (idCardNum == null || "".equals(idCardNum)) {
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
