package com.example.controller;
import com.example.utils.DBUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteProduct extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productIdStr = request.getParameter("productId");
        HttpSession session = request.getSession();
        String userIdStr =(String) session.getAttribute("userId");

        if (productIdStr != null) {
            int productId = Integer.parseInt(productIdStr);
            int userId =  Integer.parseInt(userIdStr);
            String sql = "DELETE FROM cart WHERE product_id = ? AND user_id = ?";
            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, productId);
                ps.setInt(2, userId);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    conn.commit();
                    response.sendRedirect(request.getContextPath() + "/Cart");

                } else {
                    conn.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
