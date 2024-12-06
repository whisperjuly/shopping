<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>购物车</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/client/css/style.css">
</head>
<body>
    <%@include file="header.jsp"%>
    <header class="header">
        <h1>购物车</h1>
    </header>

    <div class="coupon-banner">
        <span>天降神券！您有一张<span class="highlight">80元</span>惊喜券待领取！</span>
        <button class="btn-claim">立即领取</button>
    </div>

    <main class="shopping-cart">
        <h2>全部商品 (${cartItems.size()})</h2>
        <table>
            <thead>
                <tr>
                    <th>选择</th>
                    <th>商品</th>
                    <th>单价</th>
                    <th>数量</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${cartItems}">
                    <tr>
                        <td><input type="checkbox" /></td>
                        <td>
                            <img src="${item.imgUrl}" alt="商品图片" class="product-img">
                            <span>${item.productName}</span>
                        </td>
                        <td>￥${item.price}</td>
                        <td>
                            <input type="number" class="input-quantity" value="${item.quantity}" data-product-id="${item.productId}" />
                        </td>
                        <td>
                            <form action="<%= request.getContextPath() %>/DeleteProd" method="POST">
                                <input type="hidden" name="productId" value="${item.productId}" />
                                <button type="submit" class="btn-remove">删除</button>
                            </form>

                        </td>
                    </tr>
                </c:forEach>

            </tbody>

        </table>
    </main>
    <div class="foot-detail">
        <div class="footer-info">
            <p>免费运费 | 48小时内发货 | 7天无理由退换</p>
        </div>
    </div>
    <footer class="cart-footer">
        <span ><span id="totalAmount">合计: 0.0</span></span>
        <button class="btn-pay" id="checkoutBtn">结算</button>
    </footer>

<script>


document.addEventListener("DOMContentLoaded", function() {

    const quantityInputs = document.querySelectorAll(".input-quantity");
    quantityInputs.forEach(input => {
        input.addEventListener("input", function() {
            const productId = this.getAttribute("data-product-id");
            let quantity = parseInt(this.value);

            if (quantity < 1) {
                quantity = 1;
                this.value = 1;
            }

            updateQuantity(productId, quantity);
        });
    });

    function updateQuantity(productId, quantity) {
        const xhr = new XMLHttpRequest();
        xhr.open("POST", "<%= request.getContextPath() %>/UpdCart", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                console.log("商品数量更新成功", xhr.responseText);
            }
        };
        xhr.send("productId=" + String(productId) + "&quantity=" + String(quantity));
    }
    const checkoutBtn = document.getElementById("checkoutBtn");
    checkoutBtn.addEventListener("click", function() {
        const cartItems = [];
        const checkboxes = document.querySelectorAll("input[type='checkbox']:checked");

        checkboxes.forEach(checkbox => {
            const row = checkbox.closest('tr');
            const productId = row.querySelector(".input-quantity").getAttribute("data-product-id");
            const quantity = row.querySelector(".input-quantity").value;
            const productName = row.querySelector("span").innerText;
            const price = parseFloat(row.querySelector("td:nth-child(3)").innerText.replace("￥", ""));

            cartItems.push({
                productId: productId,
                productName: productName,
                quantity: quantity,
                price: price
            });
        });

        if (cartItems.length === 0) {
            alert("请选择要结算的商品！");
            return;
        }

        const xhr = new XMLHttpRequest();
        xhr.open("POST", "Checkout", true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                const response = JSON.parse(xhr.responseText);
                if (response.success) {
                    alert("结算成功！");
                    document.getElementById('totalAmount').innerText = `合计: $${data.totalAmount.toFixed(2)}`;
                    window.location.href = "<%= request.getContextPath() %>/Product"; // 跳转到成功页面
                } else {
                    alert("结算失败，请稍后重试！");
                }
            }
        };
        xhr.send(JSON.stringify(cartItems));
    });


});
</script>


</body>
</html>
