package cn.edu.xmut.lgrg.entity;

import cn.edu.xmut.lgrg.annotation.ZnSqlField;

/**
 * @Auther: ZiNan
 * @Date: 2019/12/30 11:10
 * @Description:
 */
public class SysEvaluate {
    @ZnSqlField(name = "evaluate_id")
    private String evaluateId;
    @ZnSqlField(name = "user_id")
    private String userId;
    @ZnSqlField(name = "com_id")
    private String comId;
    @ZnSqlField(name = "evaluate_text")
    private String evaluateText;
    @ZnSqlField(name = "order_id")
    private String orderId;

    public String getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(String evaluateId) {
        this.evaluateId = evaluateId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getEvaluateText() {
        return evaluateText;
    }

    public void setEvaluateText(String evaluateText) {
        this.evaluateText = evaluateText;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
