package com.example.domain;

public class OrderItem {
    private int id;
    private int orderId;
    private int productId;
    private String productName;
    private double price;
    private int quantity;
    private String productImage;  // 添加字段，用于存储商品图片的 URL
    private double subtotal;
    // 其他构造方法、getter 和 setter 方法

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
    public OrderItem(int id, int orderId, int productId, String productName, double price, int quantity, double subtotal) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.productName=productName;
        this.price = price;
        this.quantity = quantity;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getProductName() {
        return productName;  // 确保有 getter 方法
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public double getSubtotal() {
        return subtotal;  // 确保有 getter 方法
    }
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

}

