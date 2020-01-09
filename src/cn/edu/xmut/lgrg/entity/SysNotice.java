package cn.edu.xmut.lgrg.entity;

import cn.edu.xmut.lgrg.annotation.ZnSqlField;

/**
 * @Auther: Zhifeng
 * @Date: 2020/1/8 16:02
 * @Description:
 */
public class SysNotice {
    @ZnSqlField(name = "user_id")
    private String userId;
    @ZnSqlField(name = "user_headline")
    private String userHeadline;
    @ZnSqlField(name = "user_notice")
    private String userNotice;

    public String getUserHeadline() {
        return userHeadline;
    }

    public void setUserHeadline(String userHeadline) {
        this.userHeadline = userHeadline;
    }

    public String getUserNotice() {
        return userNotice;
    }

    public void setUserNotice(String userNotice) {
        this.userNotice = userNotice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
