package com.example.controller;
import com.example.Dao.ProductDao;
import com.example.domain.Product;
import com.example.utils.DBUtil;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class AdminProduct extends HttpServlet {

    private ProductDao productDao = new ProductDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Product> products = productDao.getAllProducts();
            request.setAttribute("products", products);
            request.getRequestDispatcher("/admin/productlist.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "数据库查询失败");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("update".equals(action)) {
                // 处理修改
                int productId = Integer.parseInt(request.getParameter("productId"));
                String productName = request.getParameter("productName");
                double price = Double.parseDouble(request.getParameter("price"));
                int stock = Integer.parseInt(request.getParameter("stock"));
                String imgUrl = request.getParameter("imgUrl");

                Product product = new Product();
                product.setId(productId);
                product.setName(productName);
                product.setPrice(price);
                product.setStock(stock);
                product.setImgUrl(imgUrl);

                if (productDao.updateProduct(product)) {
                    response.sendRedirect(request.getContextPath() + "/AdminProduct");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "修改商品失败");
                }
            } else if ("delete".equals(action)) {

                int productId = Integer.parseInt(request.getParameter("productId"));

                if (productDao.deleteProduct(productId)) {
                    response.sendRedirect(request.getContextPath() + "/AdminProduct");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "删除商品失败");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "数据库操作失败");
        }
    }
}
