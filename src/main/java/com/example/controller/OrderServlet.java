package com.example.controller;
import com.example.Dao.OrderDao;
import com.example.domain.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class OrderServlet extends HttpServlet {
    private OrderDao orderDao;

    @Override
    public void init() throws ServletException {
        orderDao = new OrderDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Order> orders = orderDao.getAllOrders();
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/admin/order.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("加载订单列表失败", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String orderIdStr = request.getParameter("orderId");

        if (action == null || orderIdStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "请求参数错误");
            return;
        }

        int orderId;
        try {
            orderId = Integer.parseInt(orderIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "订单编号无效");
            return;
        }

        try {
            if ("delete".equals(action)) {
                boolean success = orderDao.deleteOrder(orderId);
                if (success) {
                    request.setAttribute("message", "订单删除成功");
                } else {
                    request.setAttribute("error", "订单删除失败");
                }
            } else if ("update".equals(action)) {
                // 更新订单状态为完成
                boolean success = orderDao.markOrderAsCompleted(orderId);
                if (success) {
                    request.setAttribute("message", "订单状态更新成功");
                } else {
                    request.setAttribute("error", "订单状态更新失败");
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "未知操作");
                return;
            }

            // 重定向回订单管理页面
            response.sendRedirect(request.getContextPath() + "/Order");
        } catch (Exception e) {
            throw new ServletException("处理订单时出错", e);
        }
    }
}

