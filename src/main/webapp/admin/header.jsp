<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>管理员头部</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/admin/css/header.css">
</head>
<body>
<div class="header-main">
    <table cellspacing="0" class="headtable">
        <tr>
            <td>
                <%
                    String email = (String) session.getAttribute("email");
                    if (email != null) {
                %>
                    <a href="<%= request.getContextPath() %>">首页</a>
                    <a>|</a>
                    <a href="<%= request.getContextPath() %>/Logout">退出管理</a>
                <%
                    } else {
                %>

                    <a href="<%= request.getContextPath() %>/admin/companyin.jsp">登陆</a>
                <%
                    }
                %>

            </td>
             <td class="right">
                <a href="<%= request.getContextPath() %>/AdminProduct">商品管理</a>
                <a href="<%= request.getContextPath() %>/Order">订单管理</a>
                <a href="<%= request.getContextPath() %>/AdminClient">客户管理</a>
                <a href="<%= request.getContextPath() %>/Statistics">销售统计报表</a>
            </td>
        </tr>
    </table>
</div>

</body>
</html>
