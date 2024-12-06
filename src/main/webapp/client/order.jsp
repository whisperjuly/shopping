<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>商品管理</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/admin/css/product.css">
</head>
<body>
<%@ include file="header.jsp" %>

<main class="shopping-cart">
        <table>
            <thead>
                <tr>
                    <th>订单编号</th>
                    <th>下单日期</th>
                    <th>订单状态</th>
                    <th>确认收货</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.createdAt}</td>
                        <td>${order.status}</td>
                        <td>
                            <form action="/shopping/MyOrder" method="POST">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="orderId" value="${order.id}">
                                <label>${order.status}</label>
                                <button
                                    type="submit"
                                     class="btn-update ${order.status == '已完成' ? 'completed' : ''}"
                                ${order.status == '已完成' ? 'disabled' : ''}>
                                确认收货
                                </button>
                        </form>
                        </td>
                        <td><a href="/shopping/MyOrdetail?orderId=${order.id}" class="btn-details">查看详情</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </main>

</body>
