package com.example.controller;

import com.example.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String Email = request.getParameter("Email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("second_pwd");
        String phone = request.getParameter("phone");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        if (username == null || Email == null || password == null || phone == null ||
                username.isEmpty() || Email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
            response.getWriter().println("<h3>所有字段均为必填项！</h3>");
            return;
        }
        if (!password.equals(confirmPassword)) {
            response.getWriter().println("<h3>两次密码不匹配！</h3>");
            return;
        }
        if (!phone.matches("^1[3-9]\\d{9}$")) {
            response.getWriter().println("<h3>请输入有效的11位手机号！</h3>");
            return;
        }
        try (Connection connection = DBUtil.getConnection()) {
            String sql = "INSERT INTO users (UserName, Email, Password, Phone) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, Email);
                ps.setString(3, password);
                ps.setString(4, phone);
                int rows = ps.executeUpdate();

                if (rows > 0) {
                    connection.commit();
                    response.sendRedirect(request.getContextPath()+ "/client/login.jsp");
                } else {
                    connection.rollback();
                    response.getWriter().println("<h3>注册失败，请稍后再试。</h3>");
                }
            }
        } catch (SQLException e) {
            // 使用日志框架（如 Log4j 或 Java 内置日志）
            e.printStackTrace();
            response.getWriter().println("<h3>系统错误，请稍后再试。</h3>");

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 如果通过 GET 请求访问，重定向到注册页面
        response.sendRedirect("register.jsp");
    }
}
