package cn.edu.xmut.lgrg.entity;

import cn.edu.xmut.lgrg.annotation.ZnSqlField;

/**
 * @Auther: ZiNan
 * @Date: 2019/12/30 10:59
 * @Description:
 */
public class SysCommodity {
    @ZnSqlField(name = "com_id")
    private String comId;
    @ZnSqlField(name = "com_name")
    private String comName;
    @ZnSqlField(name = "com_price")
    private double comPrice;
    @ZnSqlField(name = "com_op")
    private double comOp;
    @ZnSqlField(name = "com_create_time")
    private String comCreateTime;
    @ZnSqlField(name = "com_brand_id")
    private String comBrandId;
    @ZnSqlField(name = "com_category_id")
    private String comCategoryId;
    @ZnSqlField(name = "detail_info")
    private String detailInfo;
    @ZnSqlField(name = "status")
    private String status;
    @ZnSqlField(name = "is_vip")
    private String isVip;
    @ZnSqlField(name = "com_img")
    private String comImg;
    @ZnSqlField(name = "com_stock")
    private String comStock;

    public String getComStock() {
        return comStock;
    }

    public void setComStock(String comStock) {
        this.comStock = comStock;
    }

    public String getComImg() {
        return comImg;
    }

    public void setComImg(String comImg) {
        this.comImg = comImg;
    }

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
