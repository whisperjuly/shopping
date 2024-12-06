package com.example.controller;

import com.example.Dao.OrderDao;
import com.example.domain.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class MyOrder extends HttpServlet {
    private OrderDao orderDao = new OrderDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        String userIdStr =(String)  session.getAttribute("userId");

        if (userIdStr == null) {
            response.sendRedirect("client/login.jsp"); // 如果用户未登录，重定向到登录页面
            return;
        }
        int userId = Integer.parseInt(userIdStr);
        // 查询当前用户的所有订单
        List<Order> orders = orderDao.getOrdersByUserId(userId);

        // 将订单列表传递到 JSP 页面
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("client/order.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderIdStr = request.getParameter("orderId");
        int orderId;
        try {
            orderId = Integer.parseInt(orderIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "订单编号无效");
            return;
        }
        boolean success = orderDao.markOrderAsCompleted(orderId);
        if (success) {
            request.setAttribute("message", "订单状态更新成功");
        } else {
            request.setAttribute("error", "订单状态更新失败");
        }
        response.sendRedirect(request.getContextPath() + "/MyOrder");
    }
}