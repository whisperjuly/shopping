package com.example.Dao;
import com.example.domain.Product;
import com.example.utils.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
//商品管理页面
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products"; // SQL 查询

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setImgUrl(rs.getString("imgurl"));
                products.add(product);
            }
        }
        return products;
    }

    public boolean updateProduct(Product product) throws SQLException {
        String query = "UPDATE products SET name = ?, price = ?, stock = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getStock());
            stmt.setInt(4, product.getId());
            int row=stmt.executeUpdate();
            if(row > 0) conn.commit();
            else conn.rollback();
            return row > 0;
        }
    }

    public boolean deleteProduct(int productId) throws SQLException {
        String query = "DELETE FROM products WHERE id = ?";
        Connection conn=null;
        conn = DBUtil.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            conn.setAutoCommit(false);
            stmt.setInt(1, productId);
            int row=stmt.executeUpdate();
            if(row > 0) conn.commit();
            else conn.rollback();
            return row > 0;
        }catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("删除商品失败", e);  // 重新抛出异常
        }
    }
    public void addProduct(){

    }
}

