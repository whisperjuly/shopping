package com.example.controller;
import com.example.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前月份
        int currentMonth = LocalDate.now().getMonthValue();
        double totalSales = getTotalSalesForMonth(currentMonth);
        List<Map<String, Object>> topProducts = getTop3SellingProductsForMonth(currentMonth);
        List<Map<String, Object>> topCustomers = getTop3CustomersForMonth(currentMonth);

        // 设置到请求属性
        request.setAttribute("totalSales", totalSales);
        request.setAttribute("topProducts", topProducts);
        request.setAttribute("topCustomers", topCustomers);

        // 转发到 JSP 页面
        request.getRequestDispatcher("/admin/statistic.jsp").forward(request, response);
    }

    private double getTotalSalesForMonth(int month) {
        String sql = "SELECT SUM(o.total_amount) AS total_sales " +
                "FROM `orders` o " +
                "WHERE MONTH(o.created_at) = ? AND o.status = '已完成';";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, month);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total_sales");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    private List<Map<String, Object>> getTop3SellingProductsForMonth(int month) {
        String sql ="SELECT p.name, SUM(oi.quantity) AS total_sold " +
                "FROM order_items oi " +
                "JOIN orders o ON oi.order_id = o.id " +
                "JOIN products p ON oi.product_id = p.id " +
                "WHERE MONTH(o.created_at) = ? AND o.status = '已完成' " +
                "GROUP BY p.name " +
                "ORDER BY total_sold DESC " +
                "LIMIT 3";

        List<Map<String, Object>> topProducts = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, month);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> product = new HashMap<>();
                product.put("name", rs.getString("name"));
                product.put("total_sold", rs.getInt("total_sold"));
                topProducts.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topProducts;
    }

    private List<Map<String, Object>> getTop3CustomersForMonth(int month) {
        String sql = "SELECT u.UserName AS customer_name, SUM(o.total_amount) AS total_purchase " +
                "FROM orders o " +
                "JOIN users u ON o.user_id = u.UserID " +
                "WHERE MONTH(o.created_at) = ? AND o.status = '已完成' " +
                "GROUP BY u.UserName " +
                "ORDER BY total_purchase DESC " +
                "LIMIT 3";

        List<Map<String, Object>> topCustomers = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, month);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> customer = new HashMap<>();
                customer.put("name", rs.getString("customer_name"));
                customer.put("total_purchase", rs.getDouble("total_purchase"));
                topCustomers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topCustomers;
    }
}
