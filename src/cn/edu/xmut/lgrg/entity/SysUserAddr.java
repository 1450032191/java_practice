package cn.edu.xmut.lgrg.entity;

import cn.edu.xmut.lgrg.annotation.ZnSqlField;

/**
 * @Auther: ZiNan
 * @Date: 2019/12/30 11:19
 * @Description:
 */
public class SysUserAddr {
    @ZnSqlField(name = "ua_id")
    private String uaId;
    @ZnSqlField(name = "ua_name")
    private String uaName;
    @ZnSqlField(name = "ua_phone")
    private String uaPhone;
    @ZnSqlField(name = "ua_detail_addr")
    private String uaDetailAddr;
    @ZnSqlField(name = "ua_addr")
    private String uaAddr;

    public String getUaId() {
        return uaId;
    }

    public void setUaId(String uaId) {
        this.uaId = uaId;
    }

    public String getUaName() {
        return uaName;
    }

    public void setUaName(String uaName) {
        this.uaName = uaName;
    }

    public String getUaPhone() {
        return uaPhone;
    }

    public void setUaPhone(String uaPhone) {
        this.uaPhone = uaPhone;
    }

    public String getUaDetailAddr() {
        return uaDetailAddr;
    }

    public void setUaDetailAddr(String uaDetailAddr) {
        this.uaDetailAddr = uaDetailAddr;
    }

    public String getUaAddr() {
        return uaAddr;
    }

    public void setUaAddr(String uaAddr) {
        this.uaAddr = uaAddr;
    }
}
