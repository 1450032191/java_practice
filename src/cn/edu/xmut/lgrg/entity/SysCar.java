package cn.edu.xmut.lgrg.entity;

import cn.edu.xmut.lgrg.annotation.ZnSqlField;

/**
 * @author azx
 * @create 2019-12-31-10:21
 */
public class SysCar {
    @ZnSqlField(name = "car_id")
    private String carId;
    @ZnSqlField(name = "com_quantity")
    private String comCount;
    @ZnSqlField(name = "user_id")
    private String userId;
    @ZnSqlField(name = "com_price")
    private String comPrice;
    @ZnSqlField(name = "com_id")
    private String comId;
    private double allComPrice;
    private SysCommodity commodity;

    public double getAllComPrice() {
        return allComPrice;
    }

    public void setAllComPrice(double allComPrice) {
        this.allComPrice = allComPrice;
    }

    public SysCommodity getCommodity() {
        return commodity;
    }

    public void setCommodity(SysCommodity commodity) {
        this.commodity = commodity;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getComCount() {
        return comCount;
    }

    public void setComCount(String comCount) {
        this.comCount = comCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComPrice() {
        return comPrice;
    }

    public void setComPrice(String comPrice) {
        this.comPrice = comPrice;
    }
}
