package cn.edu.xmut.lgrg.entity;

/**
 * @Auther: ZiNan
 * @Date: 2019/12/30 10:59
 * @Description:
 */
public class SysCommodity {
    private String comId;
    private String comName;
    private double comPrice;
    private double comOp;
    private String comCreateTime;
    private String comBrandId;
    private String comCategoryId;
    private String detailInfo;
    private String status;
    private String isVip;

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public double getComPrice() {
        return comPrice;
    }

    public void setComPrice(double comPrice) {
        this.comPrice = comPrice;
    }

    public double getComOp() {
        return comOp;
    }

    public void setComOp(double comOp) {
        this.comOp = comOp;
    }

    public String getComCreateTime() {
        return comCreateTime;
    }

    public void setComCreateTime(String comCreateTime) {
        this.comCreateTime = comCreateTime;
    }

    public String getComBrandId() {
        return comBrandId;
    }

    public void setComBrandId(String comBrandId) {
        this.comBrandId = comBrandId;
    }

    public String getComCategoryId() {
        return comCategoryId;
    }

    public void setComCategoryId(String comCategoryId) {
        this.comCategoryId = comCategoryId;
    }

    public String getDetailInfo() {
        return detailInfo;
    }

    public void setDetailInfo(String detailInfo) {
        this.detailInfo = detailInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }
}
