package com.example.controller;
import com.example.domain.CartItem;
import com.example.utils.DBUtil;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userIdStr =(String)  session.getAttribute("userId");

        if (userIdStr == null) {
            response.sendRedirect("client/login.jsp"); // 如果未登录，重定向到登录页面
            return;
        }
        int userId = Integer.parseInt(userIdStr);
        List<CartItem> cartItems = getCartItems(userId);
        request.setAttribute("cartItems", cartItems);
        request.getRequestDispatcher("/client/cart.jsp").forward(request, response);
    }
    private List<CartItem> getCartItems(int userId) {
        List<CartItem> cartItems = new ArrayList<>();
        String sql = "SELECT p.id, p.name, p.price, c.quantity, p.imgurl FROM cart c JOIN products p ON c.product_id = p.id WHERE c.user_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
             conn = DBUtil.getConnection();
             ps = conn.prepareStatement(sql);

            ps.setInt(1, userId);
             rs = ps.executeQuery();

            while (rs.next()) {
                CartItem item = new CartItem();
                item.setProductId(rs.getInt("id"));
                item.setProductName(rs.getString("name"));
                item.setPrice(rs.getDouble("price"));
                item.setQuantity(rs.getInt("quantity"));
                item.setImgUrl(rs.getString("imgurl"));
                cartItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) {
                    conn.close();  // 关闭连接
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return cartItems;
    }

}
