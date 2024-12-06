package com.example.controller;

import com.example.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,  // 1MB
        maxFileSize = 1024 * 1024 * 5,    // 5MB
        maxRequestSize = 1024 * 1024 * 10 // 10MB
)//图片大小
public class AddProduct extends HttpServlet {
    private static final String IMAGE_UPLOAD_DIR = "admin/images";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        addProduct(request, response);
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            String name = request.getParameter("productName");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            String category = request.getParameter("category");
            // 处理文件上传
            Part filePart = request.getPart("imgFile");
            String fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();
            // 避免文件名冲突，加时间戳
            String uploadPath = getServletContext().getRealPath("/") + IMAGE_UPLOAD_DIR;
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String filePath = uploadPath + File.separator + fileName;
            filePart.write(filePath); // 保存文件
            String relativePath = IMAGE_UPLOAD_DIR + "/" + fileName;
            // 相对路径保存到数据库

            // 数据库操作
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false); // 开启事务

            String sql = "INSERT INTO products (name, description, price, stock, category, imgurl) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setDouble(3, price);
            stmt.setInt(4, stock);
            stmt.setString(5, category);
            stmt.setString(6, relativePath);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                conn.commit(); // 提交事务
                response.sendRedirect(request.getContextPath() + "/AdminProduct");
            } else {
                conn.rollback(); // 回滚事务
                throw new ServletException("Failed to insert product into database.");
            }
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback(); // 出现异常时回滚事务
                } catch (Exception rollbackEx) {
                    e.addSuppressed(rollbackEx); // 记录回滚异常
                }
            }
            throw new ServletException("Error adding product", e);
        } finally {
            // 释放资源
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception closeEx) {
                    closeEx.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }
}
