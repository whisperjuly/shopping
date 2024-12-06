package com.example.domain;


import java.io.Serializable;

public class User implements Serializable {

    private int id;         // 用户ID
    private String name;    // 用户名
    private String email;   // 用户邮箱
    private String phone;   // 用户电话

    // 无参数构造函数
    public User() {}

    // 带参数的构造函数
    public User(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Getters 和 Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email + "', phone='" + phone + "'}";
    }
}
