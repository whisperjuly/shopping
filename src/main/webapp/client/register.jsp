<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/client/css/main.css">
</head>

<body >
    <%@include file="header.jsp"%>
    <div class="register-main">
    <div class="sign-up">
        <form action="<%= request.getContextPath() %>/Register" method="post" class="signform">
            <h1>注册</h1>
                <input type="text"  id="username" name="username" placeholder="请输入用户名" />
    			<input type="text"  id="Email" name="Email" placeholder="电子邮箱" />
                <input type="password" id="pwd" name="password" placeholder="请输入密码" />
                <input type="password" id="second_pwd" name="second_pwd" placeholder="请再次输入密码" />
    			<input type="text" id="phone" name="phone" placeholder="手机号码" />

                <button>注册</button>
        </form>
    </div>
     </div>
</body>
</html>