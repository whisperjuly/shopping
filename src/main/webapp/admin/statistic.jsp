<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>销售统计</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/admin/css/statistic.css">

</head>
<body>
    <%@include file="header.jsp"%>
    <main class="shopping-cart">
    <h2>销售统计</h2>


<div class="section">
    <h2>本月销售额</h2>
    <table class="statistics-table">
        <thead>
            <tr>
                <th>月份</th>
                <th>总销售</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>本月</td>
                <td>${totalSales}</td>
            </tr>
        </tbody>
    </table>
</div>
<div class="section">
        <h2>热销商品</h2>
        <table class="statistics-table">
            <thead>
                <tr>
                    <th>Month</th>
                    <th>商品名</th>
                    <th>销售额</th>
                </tr>
            </thead>
            <tbody>
                 <c:forEach items="${topProducts}" var="product" varStatus="status">
                     <tr>
                        <td>${status.index + 1}</td>
                        <td>${product.name}</td>
                        <td>${product.total_sold}</td>
                    </tr>
                </c:forEach>
        </tbody>
        </table>
    </div>

    <!-- Top 3 Customers Table -->
    <div class="section">
        <h2>消费前三客户</h2>
        <table class="statistics-table">
            <thead>
                <tr>
                    <th>Rank</th>
                    <th>顾客名</th>
                    <th>消费额</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${topCustomers}" var="customer" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${customer.name}</td>
                        <td>${customer.total_purchase}</td>
                    </tr>
                 </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="footer">
        <p>&copy; 2024 Sales Analytics. All rights reserved.</p>
    </div>
    </main>
</body>
</html>
