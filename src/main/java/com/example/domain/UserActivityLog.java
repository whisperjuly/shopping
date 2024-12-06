package com.example.domain;

import java.sql.Timestamp;

public class UserActivityLog {
    private int userId;          // 用户 ID
    private int productId;       // 商品 ID
    private String productName;  // 商品名称
    private String actionType;   // 操作类型（加入购物车 或 购买）
    private Timestamp activityTime; // 操作时间

    // 无参构造器
    public UserActivityLog() {
    }

    // 全参构造器
    public UserActivityLog(int userId, int productId, String productName, String actionType, Timestamp activityTime) {
        this.userId = userId;
        this.productId = productId;
        this.productName = productName;
        this.actionType = actionType;
        this.activityTime = activityTime;
    }

    // Getter 和 Setter 方法
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public Timestamp getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Timestamp activityTime) {
        this.activityTime = activityTime;
    }

    // 重写 toString 方法
    @Override
    public String toString() {
        return "UserActivityLog{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", actionType='" + actionType + '\'' +
                ", actionTime=" + activityTime +
                '}';
    }
}
