package cn.edu.xmut.lgrg.entity;

import cn.edu.xmut.lgrg.annotation.ZnSqlField;

/**
 * @Auther: ZiNan
 * @Date: 2019/12/30 11:17
 * @Description:
 */
public class SysUser {
    @ZnSqlField(name = "user_id")
    private String userId;
    @ZnSqlField(name = "user_name")
    private String userName;
    @ZnSqlField(name = "user_pass")
    private String userPass;
    @ZnSqlField(name = "user_email")
    private String userEmail;
    @ZnSqlField(name = "user_phone")
    private String userPhone;
    @ZnSqlField(name = "status")
    private String status;
    @ZnSqlField(name = "user_image")
    private String userImage;
    @ZnSqlField(name = "user_is_admin")
    private String userIsAdmin;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserIsAdmin() {
        return userIsAdmin;
    }

    public void setUserIsAdmin(String userIsAdmin) {
        this.userIsAdmin = userIsAdmin;
    }
}
