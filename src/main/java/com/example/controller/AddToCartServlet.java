package com.example.controller;
import com.example.utils.DBUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class AddToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String userIdStr = request.getParameter("userId");
        if (userIdStr == null ) {
            response.sendRedirect("client/login.jsp");
            return;
        }
        int productId = Integer.parseInt(request.getParameter("productId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int selected = Integer.parseInt(request.getParameter("selected"));
        Connection conn=null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            String sql = "INSERT INTO cart (product_id, quantity, selected, user_id) " +
                    "VALUES (?, ?, ?, ?) " +
                    "ON DUPLICATE KEY UPDATE quantity = quantity + VALUES(quantity)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, productId);
                stmt.setInt(2, quantity);
                stmt.setInt(3, selected);
                stmt.setInt(4, userId);
                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    conn.commit();
                    response.sendRedirect(request.getContextPath() + "/Detail?id=" + productId + "&success=true");
                } else {
                     conn.rollback();
                    response.sendRedirect(request.getContextPath() + "/Detail?id=" + productId + "&success=false");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            response.sendRedirect(request.getContextPath() + "/Detail?id=" + productId + "&success=false");
        } finally {
            try {
                if (conn != null) {
                    conn.close();  // 关闭连接
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

