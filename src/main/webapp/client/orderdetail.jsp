<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/admin/css/orderdetail.css">
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        img {
            max-width: 100px;
            max-height: 110px;
            object-fit: cover;
        }
    </style>
</head>
<body>
    <h1>订单详情</h1>
    <h2>订单编号：${order.id}</h2>
    <p>客户 ID：${order.userId}</p>
    <p>订单状态：${order.status}</p>
    <p>下单时间：${order.createdAt}</p>
    <p>总金额：${order.totalAmount} 元</p>

    <h3>购买的商品</h3>
    <table>
        <thead>
            <tr>
                <th>商品图片</th>
                <th>商品名称</th>
                <th>单价</th>
                <th>数量</th>
                <th>小计</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${orderItems}">
                <tr>
                    <td>
                        <img src="${item.productImage}" alt="${item.productName}">
                    </td>
                    <td>${item.productName}</td>
                    <td>${item.price}</td>
                    <td>${item.quantity}</td>
                    <td>${item.subtotal}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <a href="/shopping/MyOrder">返回订单列表</a>
</body>
</html>
