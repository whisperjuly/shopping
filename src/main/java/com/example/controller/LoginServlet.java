package com.example.controller;

import com.example.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // 获取前端传来的参数
        String mode = request.getParameter("mode"); // 登录模式（用户名或邮箱）
        String identifier = request.getParameter("identifier");
        String password = request.getParameter("password");
        try (PrintWriter out = response.getWriter()) {
            if (validateUser(mode, identifier, password,request)) {
                HttpSession session = request.getSession();
                session.setAttribute("username", identifier);
                response.sendRedirect("index.jsp");
            } else {
                if ("username".equals(mode)) {
                    request.setAttribute("error", "用户名或密码错误！");
                } else {
                    request.setAttribute("error", "邮箱或密码错误！");
                }
                request.setAttribute("mode", mode);  // 传递当前登录模式，保持表单切换状态
                request.getRequestDispatcher("/client/login.jsp").forward(request, response);
            }

        }
    }

    private boolean validateUser(String mode, String identifier, String password,HttpServletRequest request) {
        boolean isValid = false;
        String query;
        String userId = null;
        if ("username".equals(mode)) {
            query = "SELECT UserID FROM users WHERE UserName = ? AND Password = ?";
        } else {
            query = "SELECT UserID FROM users WHERE Email = ? AND Password = ?";
        }

        try (Connection conn = DBUtil.getConnection()){
            try( PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, identifier);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                isValid = rs.next();
                if (isValid) {
                    userId = rs.getString("UserID");  // 获取用户ID
                }
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        if (isValid && userId != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", userId);  // 存储用户ID到session
        }

        return isValid;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

