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
    private Double comPrice;
    @ZnSqlField(name = "com_op")
    private Double comOp;
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
    @ZnSqlField(name = "brand_name")
    private String brandName;
    @ZnSqlField(name = "category_name")
    private String categoryName;
    @ZnSqlField(name = "sales")
    private String sales;

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    private Integer carCouont;

    public Integer getCarCouont() {
        return carCouont;
    }

    public void setCarCouont(Integer carCouont) {
        this.carCouont = carCouont;
    }

    public SysCommodity(){
        comPrice = 0.0;
        comOp = 0.0;
    }

    public void setComPrice(Double comPrice) {
        this.comPrice = comPrice;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

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

    public Double getComOp() {
        return comOp;
    }

    public void setComOp(Double comOp) {
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
