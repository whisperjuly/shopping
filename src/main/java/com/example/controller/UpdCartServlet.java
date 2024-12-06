package com.example.controller;
import com.example.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        String quantity = request.getParameter("quantity");
        HttpSession session = request.getSession();
        String userIdStr =(String)  session.getAttribute("userId");

        if (productId != null && quantity != null) {
            int productIdInt = Integer.parseInt(productId);
            int quantityInt = Integer.parseInt(quantity);
            int userIdInt = Integer.parseInt(userIdStr);

            updateCartItemQuantity(productIdInt, quantityInt,userIdInt);
            response.getWriter().write("数量更新成功");
        } else {
            response.getWriter().write("无效的请求");
        }
    }

    private void updateCartItemQuantity(int productId, int quantity,int userId) {
        String sql = "UPDATE cart SET quantity = ? WHERE product_id = ? AND user_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, productId);

            stmt.setInt(3, userId);
            int row=stmt.executeUpdate();
            if(row>0){
                conn.commit();
                System.out.println("commit success");
            }
            else{
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}