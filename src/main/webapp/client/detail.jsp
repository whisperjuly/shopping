<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.example.domain.Product" %>
<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Detail</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/client/css/detail.css">


</head>
<body>
    <%@include file="header.jsp"%>
    <div class="product-container">
        <div class="product-main">
            <div class="product-images">
                <img src="<c:url value='/${product.imgUrl}' />" alt="Product Image 1" class="product-main-image">
            </div>

            <div class="product-details">
                <h1 class="product-name">${product.name}</h1>
                <p class="product-description">${product.description}</p>

                <div class="product-price">
                    <span class="price">¥${product.price}</span>
                    <span class="count"> 已售: 1000+</span>
                    <p class="stock">库存：${product.stock}</p>

                </div>
                <div class="product-actions">
                    <button class="buy-now-btn">立即购买</button>
                    <form action="<%= request.getContextPath() %>/AddToCart" method="POST">
                        <input type="hidden" name="productId" value="${product.id}" />
                        <input type="hidden" name="userId" value="${sessionScope.userId}" />
                        <input type="hidden" name="quantity" value="1" />
                        <input type="hidden" name="selected" value="0" />
                        <button type="submit" class="add-to-cart-btn">加入购物车</button>
                    </form>
                </div>
            </div>
        </div>

    </div>
        <c:if test="${not empty param.success}">
                <div id="successMessage">
                    商品已成功加入购物车
                </div>
                <script>
                    // 显示提示消息
                    var successMessage = document.getElementById('successMessage');
                    successMessage.style.display = 'block';

                    // 过渡效果：逐渐显示提示框
                    setTimeout(function() {
                        successMessage.style.opacity = 1;
                    }, 10);  // 稍微延迟，以便过渡生效

                    // 2秒后隐藏提示
                    setTimeout(function() {
                        successMessage.style.opacity = 0;  // 使其渐隐
                        setTimeout(function() {
                            successMessage.style.display = 'none';  // 完全隐藏
                        }, 500);  // 等待过渡效果完成后隐藏
                    }, 2000);
                </script>
            </c:if>



        <footer>
            <div class="footer-info">
                <p>免费运费 | 48小时内发货 | 7天无理由退换</p>
            </div>
            <div class="footer-social">
                <img src="" alt="QR Code for purchase">
            </div>
        </footer>
</body>

</html>
