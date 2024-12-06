package com.example.domain;


import java.sql.Timestamp;
import java.util.List;

public class Order {

    private int id; // 订单ID
    private int userId; // 用户ID
    private double totalAmount; // 总金额
    private String status; // 订单状态
    private Timestamp createdAt; // 创建时间
    private List<OrderItem> orderItems; // 存储该订单的所有商品项

    // 省略其他构造方法、getter、setter等

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems; // 设置订单项列表
    }

    public List<OrderItem> getOrderItems() {
        return this.orderItems; // 获取订单项列表
    }
    // 默认构造方法
    public Order() {
    }

    // 全参构造方法
    public Order(int id, int userId, double totalAmount, String status, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getter 和 Setter 方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // 重写 toString 方法，方便调试
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

