<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>管理员登录</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/client/css/login.css">
    <script>
        function showError(message, errorDivId) {
            const errorDiv = document.getElementById(errorDivId);
            errorDiv.innerText = message; // 设置错误信息
            errorDiv.style.display = 'block'; // 显示错误信息
        }
        function hideError(errorDivId) {
            const errorDiv = document.getElementById(errorDivId);
            errorDiv.style.display = 'none'; // 隐藏错误信息
        }
        function validateForm(event) {
                const email = document.getElementById('email').value;
                const password = document.getElementById('password-email').value;
                if (email === '' || password === '') {
                    showError('邮箱或密码不能为空！', 'email-error');
                }
            return true;
        }
    </script>
</head>
<body>
    <%@include file="header.jsp"%>
    <div class="login-main">
    <div class="login-container">
        <div class="login-header">
            <h2>登录</h2>
        </div>
        <div id="email-login" class="login-box">
            <form action="<%= request.getContextPath() %>/AdminIn" method="POST">
                <div class="input-field">
                    <label for="email">邮箱：</label>
                    <input type="email" id="email" name="email" required />
                </div>
                <div class="input-field">
                    <label for="password-email">密码：</label>
                    <input type="password" id="password-email" name="password" required />
                </div>
                <input type="hidden" name="mode" value="email">
                <% if (request.getAttribute("error") != null) { %>
                    <div class="error-message">${error}</div>
                <% } %>
                <button type="submit" class="login-btn">登录</button>
            </form>
        </div>
        <a href="<%= request.getContextPath() %>/client/login.jsp">用户登陆<a>
    </div>
    </div>
</body>
</html>
