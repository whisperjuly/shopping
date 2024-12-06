package com.example.Dao;

import com.example.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesDao {
    // 获取12月销售最高的三个产品
    public List<Map<String, Object>> getTop3SellingProductsForDecember() {
        String sql = "SELECT p.name, SUM(s.quantity) AS total_sold " +
                "FROM sales s " +
                "JOIN products p ON s.product_id = p.id " +
                "WHERE MONTH(s.sale_date) = 12 " +
                "GROUP BY p.name " +
                "ORDER BY total_sold DESC LIMIT 3";

        List<Map<String, Object>> topProducts = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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

}
