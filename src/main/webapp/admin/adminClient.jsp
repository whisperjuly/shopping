<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>客户管理</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/admin/css/product.css">
</head>
<body>
<%@ include file="header.jsp" %>


<main class="shopping-cart">
<div class="button-container">
    <form action="/shopping/Log" method="GET">
        <button class="log-btn" id="addProductBtn">查看日志</button>
    </form>
</div>

<table border="1">
    <thead>
            <tr>
                <th>用户ID</th>
                <th>用户名</th>
                <th>邮箱</th>
                <th>电话</th>
                <th>操作</th>
            </tr>
    </thead>
    <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.phone}</td>
                    <td>
                        <form action="/shopping/AdminClient" method="POST" style="display:inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="userId" value="${user.id}">
                            <button type="submit" class="btn-remove">删除</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>

    </tbody>
</table>
</main>
