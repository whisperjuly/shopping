package com.example.controller;
import com.example.utils.DBUtil;
import jakarta.servlet.ServletException;
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

public class AdminInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String identifier = request.getParameter("email");
        String password = request.getParameter("password");

        try (PrintWriter out = response.getWriter()) {
            if (validate(identifier, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("email", identifier);
                response.sendRedirect("AdminProduct");
            } else {
                    request.setAttribute("error", "邮箱或密码错误！");
                    request.getRequestDispatcher("/admin/companyin.jsp").forward(request, response);
                }

            }

        }

    private boolean validate(String identifier, String password) {
        boolean isValid = false;
        String query = "SELECT * FROM admin WHERE email = ? AND password = ?";

        try (Connection conn = DBUtil.getConnection()){
            try( PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, identifier);
                stmt.setString(2, password);
                try (ResultSet rs = stmt.executeQuery()) {
                    isValid = rs.next();
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
