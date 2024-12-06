<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>商品管理</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/admin/css/product.css">
</head>
<body>
<%@ include file="header.jsp" %>

<main class="shopping-cart">
        <div class="button-container">
            <button class="add-product-btn" id="addProductBtn">添加商品</button>
        </div>
        <!-- 弹窗 -->
        <div id="productModal" class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close-btn" id="closeModal">&times;</span>
                </div>
                <form class="add-form" action="/shopping/AddProduct" method="POST" enctype="multipart/form-data">
                    <label for="productName">商品名称：</label>
                    <input type="text" name="productName" required><br>
                    <label for="price">单价：</label>
                    <input type="text" name="price" required><br>
                    <label for="description">商品描述：</label>
                    <input type="text" name="description" required><br>
                    <label for="category">类别：</label>
                    <select name="category" required>
                        <option value="digital-products">数码产品</option>
                        <option value="fashion">服饰美妆</option>
                        <option value="snacks">零食生鲜</option>
                        <option value="furniture">家居用品</option>
                        <option value="book-fun">娱乐图书</option>
                    </select><br>
                    <label for="stock">库存：</label>
                    <input type="text" name="stock" required><br>
                    <label for="imgUrl">图片 URL：</label>
                    <input type="file" name="imgFile" required><br>
                    <button type="submit">提交</button>
                </form>
            </div>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>商品</th>
                    <th>操作:</th>
                    <th>删除商品</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td>${product.id}</td>
                        <td>
                            <img src="${product.imgUrl}" alt="商品图片" class="product-img">
                        <span class="product-name">${product.name}</span>
                        </td>
                    <td>
                        <form action="/shopping/AdminProduct" method="POST">
                            <input type="hidden" name="action" value="update">
                            <label for="productName">名称：</label>
                            <input type="text" name="productName" value="${product.name}">
                            <input type="hidden" name="productId" value="${product.id}">
                            <label for="price">单价：</label>
                            <input type="text" name="price" value="${product.price}">
                            <label for="stock">库存：</label>
                            <input type="text" name="stock" value="${product.stock}">
                            <button type="submit" class="btn-update">修改</button>
                        </form>
                    </td>
                    <td>
                        <form action="/shopping/AdminProduct" method="POST" style="display:inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="productId" value="${product.id}">
                            <button type="submit" class="btn-remove">删除</button>
                        </form>
                    </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </main>

</body>
<script>
            // 获取按钮和弹窗元素
            var modal = document.getElementById("productModal");
            var addProductBtn = document.getElementById("addProductBtn");
            var closeModal = document.getElementById("closeModal");

            // 点击“添加商品”按钮时打开弹窗
            addProductBtn.onclick = function() {
                modal.style.display = "block";
            }

            // 点击关闭按钮时关闭弹窗
            closeModal.onclick = function() {
                modal.style.display = "none";
            }

            // 点击弹窗外部区域时关闭弹窗
            window.onclick = function(event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
</script>