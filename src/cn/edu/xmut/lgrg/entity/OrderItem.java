package cn.edu.xmut.lgrg.entity;

import cn.edu.xmut.lgrg.annotation.ZnSqlField;

/**
 * @Auther: ZiNan
 * @Date: 2020/1/7 20:31
 * @Description:
 */
public class OrderItem {

    @ZnSqlField(name = "order_detail_id")
    private String orderDetailId;
    @ZnSqlField(name = "order_no")
    private String orderNo;
    @ZnSqlField(name = "order_id")
    private String orderId;
    @ZnSqlField(name = "com_id")
    private String comId;
    @ZnSqlField(name = "com_price")
    private String comPrice;
    @ZnSqlField(name = "com_count")
    private String comCount;
    @ZnSqlField(name = "com_total_price")
    private String comTotalPrice;

    private SysCommodity commodity;

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

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getComPrice() {
        return comPrice;
    }

    public void setComPrice(String comPrice) {
        this.comPrice = comPrice;
    }

    public String getComCount() {
        return comCount;
    }

    public void setComCount(String comCount) {
        this.comCount = comCount;
    }

    public String getComTotalPrice() {
        return comTotalPrice;
    }

    public void setComTotalPrice(String comTotalPrice) {
        this.comTotalPrice = comTotalPrice;
    }
}
