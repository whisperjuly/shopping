package com.example.controller;

import com.example.Dao.UserDao;
import com.example.domain.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class AdminClient extends HttpServlet {

    private UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<User> users = userDao.getAllUsers();
            request.setAttribute("users", users);  // 将用户信息传递到前端
        request.getRequestDispatcher("admin/adminClient.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        if (userDao.deleteUser(userId)) {
            response.sendRedirect(request.getContextPath() + "/AdminClient");
        }


    }
}
