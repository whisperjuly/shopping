<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>公共头部</title>
    <!-- 引入CSS文件 -->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/client/css/header.css">
</head>
<body>
<div class="header-main">
    <table cellspacing="0" class="headtable">
        <tr>
            <td>
                <a href="<%= request.getContextPath() %>">首页</a>
                <%
                    String username = (String) session.getAttribute("username");
                    if (username != null) {
                %>
                    <a href="<%= request.getContextPath() %>/Logout">注销</a>
                    <a>|</a>
                    欢迎，<%= username %>！

                <%
                    } else {
                %>
                    <a href="<%= request.getContextPath() %>/client/register.jsp">注册</a>
                    <a>|</a>
                    <a href="<%= request.getContextPath() %>/client/login.jsp">登陆</a>
                <%
                    }
                %>

            </td>
             <td class="right">
                <a href="<%= request.getContextPath() %>/Cart">购物车</a>
                 <a href="<%= request.getContextPath() %>/MyOrder">我的订单</a>
            </td>
        </tr>
    </table>
</div>

</body>
</html>
