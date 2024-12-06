package com.example.controller;

import com.example.Dao.UserActivityLogDao;
import com.example.domain.User;
import com.example.domain.UserActivityLog;
import com.example.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import java.util.List;

public class LogServlet extends HttpServlet {
    private UserActivityLogDao logDao = new UserActivityLogDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String Str =(String)  session.getAttribute("email");
        if (Str == null) {
            response.sendRedirect("admin/companyin.jsp"); // 如果未登录，重定向到登录页面
            return;
        }

        String actionType = request.getParameter("actionType");
        List<UserActivityLog> logs = null;

        if ("purchase".equals(actionType)) {
            logs = logDao.getPurchaseLogs();
        } else if ("browse".equals(actionType)) {
            logs = logDao.getBrowseLogs();
        }

        // 将查询结果传递给前端
        request.setAttribute("logs", logs);
        request.getRequestDispatcher("admin/log.jsp").forward(request, response);
    }
}
