<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/client/css/login.css"> <!-- 引入样式文件 -->
    <script>
        function toggleLoginMode(mode) {
            if (mode === 'username') {
                document.getElementById('username-login').style.display = 'block';
                document.getElementById('email-login').style.display = 'none';
            } else if (mode === 'email') {
                document.getElementById('username-login').style.display = 'none';
                document.getElementById('email-login').style.display = 'block';
            }
        }
        function showError(message, errorDivId) {
            const errorDiv = document.getElementById(errorDivId);
            errorDiv.innerText = message; // 设置错误信息
            errorDiv.style.display = 'block'; // 显示错误信息
        }

        function hideError(errorDivId) {
            const errorDiv = document.getElementById(errorDivId);
            errorDiv.style.display = 'none'; // 隐藏错误信息
        }

        function validateForm(event, mode) {
            if (mode === 'username') {
                const username = document.getElementById('username').value;
                const password = document.getElementById('password-username').value;
                if (username === '' || password === '') {
                    showError('用户名或密码不能为空！', 'username-error');
                }
            } else if (mode === 'email') {
                const email = document.getElementById('email').value;
                const password = document.getElementById('password-email').value;
                if (email === '' || password === '') {
                    showError('邮箱或密码不能为空！', 'email-error');
                }
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
            <button class="toggle-btn" onclick="toggleLoginMode('username')">用户名登录</button>
            <button class="toggle-btn" onclick="toggleLoginMode('email')">邮箱登录</button>
        </div>

        <!-- 用户名登录 -->
        <div id="username-login" class="login-box">
            <form action="<%= request.getContextPath() %>/Login" method="POST">
                <div class="input-field">
                    <label for="username">用户名：</label>
                    <input type="text" id="username" name="identifier" required />
                </div>
                <div class="input-field">
                    <label for="password-username">密码：</label>
                    <input type="password" id="password-username" name="password" required />
                </div>
                <div id="username-error" class="error-message" style="display: none;"></div>
                <% if (request.getAttribute("error") != null) { %>
                    <div class="error-message"><%= request.getAttribute("error") %></div>
                <% } %>
                <input type="hidden" name="mode" value="username">
                <button type="submit" class="login-btn">登录</button>
            </form>
        </div>

        <!-- 邮箱登录 -->
        <div id="email-login" class="login-box" style="display: none;">
            <form action="<%= request.getContextPath() %>/Login" method="POST">
                <div class="input-field">
                    <label for="email">邮箱：</label>
                    <input type="email" id="email" name="identifier" required />
                </div>
                <div class="input-field">
                    <label for="password-email">密码：</label>
                    <input type="password" id="password-email" name="password" required />
                </div>
                <input type="hidden" name="mode" value="email">
                <% if (request.getAttribute("error") != null) { %>
                    <div class="error-message"><%= request.getAttribute("error") %></div>
                <% } %>
                <button type="submit" class="login-btn">登录</button>
            </form>
        </div>
        <a href="<%= request.getContextPath() %>/admin/companyin.jsp">管理员登陆<a>
    </div>
    </div>
</body>
</html>
