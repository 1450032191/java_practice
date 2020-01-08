package cn.edu.xmut.lgrg.entity;

import cn.edu.xmut.lgrg.annotation.ZnSqlField;

import java.util.List;

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
    @ZnSqlField(name = "user_name")
    private String userName;
    @ZnSqlField(name = "order_count")
    private String orderCount;
    @ZnSqlField(name = "user_phone")
    private String userPhone;

    private String orderStatusText;

    private Integer comCount;

    List<OrderItem> orderItems;

    public String getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(String orderCount) {
        this.orderCount = orderCount;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrderStatusText() {
        return orderStatusText;
    }

    public void setOrderStatusText(String orderStatusText) {
        this.orderStatusText = orderStatusText;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Integer getComCount() {
        return comCount;
    }

    public void setComCount(Integer comCount) {
        this.comCount = comCount;
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
