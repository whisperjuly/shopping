<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>客户管理</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/admin/css/log.css">
</head>
<body>
<%@ include file="header.jsp" %>


<main class="shopping-cart">
    <form class="search" method="GET" action="Log">
        <label for="actionType">选择操作：</label>
        <select name="actionType" id="actionType">
            <option value="purchase">购买</option>
            <option value="browse">浏览</option>
        </select>
        <button class="log-btn" type="submit">查看</button>
    </form>
    <a href="/shopping/AdminClient">返回客户列表</a>
<table border="1">
    <thead>
            <tr>
                <th>用户ID</th>
                <th>商品名称</th>
                <th>操作时间</th>
                <th>操作类型</th>
            </tr>
    </thead>
    <tbody>
            <c:forEach var="log" items="${logs}">
                <tr>
                    <td>${log.userId}</td>
                    <td>${log.productName}</td>
                    <td>${log.activityTime}</td>
                    <td>${log.actionType}</td>
                </tr>
            </c:forEach>
    </tbody>
</table>
</main>
