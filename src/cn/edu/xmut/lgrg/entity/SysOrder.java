package cn.edu.xmut.lgrg.entity;

import cn.edu.xmut.lgrg.annotation.ZnSqlField;

/**
 * @Auther: ZiNan
 * @Date: 2019/12/30 11:13
 * @Description:
 */
public class SysOrder {
    @ZnSqlField(name = "order_no")
    private String orderNo;
    @ZnSqlField(name = "order_id")
    private String orderId;
    @ZnSqlField(name = "create_time")
    private String createTime;
    @ZnSqlField(name = "user_id")
    private String userId;
    @ZnSqlField(name = "addr_id")
    private String addrId;
    @ZnSqlField(name = "order_price")
    private String orderPrice;
    @ZnSqlField(name = "order_paymethod")
    private String orderPaymethod;
    @ZnSqlField(name = "order_status")
    private String orderStatus;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddrId() {
        return addrId;
    }

    public void setAddrId(String addrId) {
        this.addrId = addrId;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderPaymethod() {
        return orderPaymethod;
    }

    public void setOrderPaymethod(String orderPaymethod) {
        this.orderPaymethod = orderPaymethod;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
