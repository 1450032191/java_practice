package cn.edu.xmut.lgrg.entity;

import cn.edu.xmut.lgrg.annotation.ZnSqlField;

/**
 * @Auther: ZiNan
 * @Date: 2020/1/9 10:16
 * @Description:
 */
public class SysNotic {
    @ZnSqlField(name = "read_user_id")
    private String readUserId;
    @ZnSqlField(name = "notic_id")
    private String noticId;
    @ZnSqlField(name = "read_status")
    private String readStatus;
    @ZnSqlField(name = "read_time")
    private String readTime;
    @ZnSqlField(name = "notice_time")
    private String noticeTime;
    @ZnSqlField(name = "user_headline")
    private String userHeadline;
    @ZnSqlField(name = "user_notice")
    private String userNotice;
    @ZnSqlField(name = "send_user_id")
    private String sendUserId;
    @ZnSqlField(name = "send_user_name")
    private String sendUserName;
    @ZnSqlField(name = "accept_count")
    private String acceptCount;
    @ZnSqlField(name = "read_count")
    private String readCount;
    @ZnSqlField(name = "no_read_count")
    private String noReadCount;

    public String getAcceptCount() {
        return acceptCount;
    }

    public void setAcceptCount(String acceptCount) {
        this.acceptCount = acceptCount;
    }

    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    public String getNoReadCount() {
        return noReadCount;
    }

    public void setNoReadCount(String noReadCount) {
        this.noReadCount = noReadCount;
    }

    public String getReadUserId() {
        return readUserId;
    }

    public void setReadUserId(String readUserId) {
        this.readUserId = readUserId;
    }

    public String getNoticId() {
        return noticId;
    }

    public void setNoticId(String noticId) {
        this.noticId = noticId;
    }

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    public String getReadTime() {
        return readTime;
    }

    public void setReadTime(String readTime) {
        this.readTime = readTime;
    }

    public String getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(String noticeTime) {
        this.noticeTime = noticeTime;
    }

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

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getSendUserName() {
        return sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }
}
