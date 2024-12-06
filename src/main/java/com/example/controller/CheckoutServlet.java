package com.example.controller;

import com.example.utils.DBUtil;
import com.example.domain.CartItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class CheckoutServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //接受前端传来的商品列表
        List<CartItem> cartItems = objectMapper.readValue(request.getReader(), new TypeReference<List<CartItem>>() {});
        System.out.println("Received cart items: " + cartItems);
        double totalAmount = 0.0;
        for (CartItem item : cartItems) {
            totalAmount += item.getPrice() * item.getQuantity();
        }
        try (Connection conn = DBUtil.getConnection()) {
            conn.setAutoCommit(false);
            String orderSql = "INSERT INTO orders (user_id, total_amount, created_at) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
            HttpSession session = request.getSession();
            String userIdStr =(String)  session.getAttribute("userId");

            int userId = Integer.parseInt(userIdStr);
            ps.setInt(1, userId);
            ps.setDouble(2, totalAmount);
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {

                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);
                    System.out.println("Generated order ID: " + orderId);
                    String orderItemSql = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement psOrderItem = conn.prepareStatement(orderItemSql)) {
                        for (CartItem item : cartItems) {
                            psOrderItem.setInt(1, orderId);
                            psOrderItem.setInt(2, item.getProductId());
                            psOrderItem.setInt(3, item.getQuantity());
                            psOrderItem.setDouble(4, item.getPrice());
                            psOrderItem.addBatch();
                        }
                        psOrderItem.executeBatch();
                    }
                }
            }

            conn.commit();

            response.setContentType("application/json");
            response.getWriter().write("{\"success\": true, \"totalAmount\": " + totalAmount + "}");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("{\"success\": false, \"error\": \"" + e.getMessage() + "\"}");
            try (Connection conn = DBUtil.getConnection()) {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": false}");
        }
    }
}

