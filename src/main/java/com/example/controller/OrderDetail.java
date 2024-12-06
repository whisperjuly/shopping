package com.example.controller;

import com.example.Dao.OrderDao;
import com.example.domain.Order;
import com.example.domain.OrderItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderDetail extends HttpServlet {
    private OrderDao orderDao = new OrderDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取订单 ID
        String orderIdStr = request.getParameter("orderId");
        if (orderIdStr == null || orderIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "订单 ID 不能为空");
            return;
        }

        int orderId;
        try {
            orderId = Integer.parseInt(orderIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "无效的订单 ID");
            return;
        }

        // 查询订单详情
        Order order = orderDao.getOrderById(orderId);
        if (order == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "订单不存在");
            return;
        }

        // 查询订单商品
        List<OrderItem> orderItems = orderDao.getOrderItemsByOrderId(orderId);

        // 设置 JSP 渲染数据
        request.setAttribute("order", order);
        request.setAttribute("orderItems", orderItems);

        // 转发到订单详情 JSP 页面
        request.getRequestDispatcher("admin/orderdetail.jsp").forward(request, response);
    }
}
