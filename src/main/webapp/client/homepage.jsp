<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>购物商城</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/client/css/homepage.css">
</head>
<body class="homepage-main">
    <%@include file="header.jsp"%>
    <div class="layout">
        <div class="search-box">
            <div class="search">
                <input type="text" class="text" placeholder="请输入搜索内容">
                <button>搜索</button>
            </div>
        </div>
        <div class="lunbo-main">
            <div class="nav-service">
                <div class="nav-service-container">
                    <div class="cate-title">分类</div>
                    <ul class="service-bd">
                        <li>
                            <a href="#digital-products">
                                <div class="first-box">
                                    <img src="<%= request.getContextPath() %>/client/images/pc-computer.png" width="24" height="24">
                                    <span class="cate-name">数码产品</span>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#fashion">
                                <div class="second-box">
                                    <img src="<%= request.getContextPath() %>/client/images/makeup.png" width="24" height="24">
                                    <span class="cate-name">服饰美妆</span>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#snacks">
                                <div class="third-box">
                                    <img src="<%= request.getContextPath() %>/client/images/hamburguer.png" width="24" height="24">
                                    <span class="cate-name">零食生鲜</span>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#furniture">
                                <div class="fourth-box">
                                    <img src="<%= request.getContextPath() %>/client/images/bookshelf.png" width="24" height="24">
                                    <span class="cate-name">家具用品</span>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#book-fun">
                                <div class="fifth-box">
                                    <img src="<%= request.getContextPath() %>/client/images/bookstack_libr_3024.png" width="24" height="24">
                                    <span class="cate-name">娱乐图书</span>
                                </div>
                            </a>
                        </li>
                    </ul>

                </div>
            </div>
            <div class="homepage-photo">
                <img src="<%= request.getContextPath() %>/client/images/home.jpg" >
            </div>
        </div>
    </div>
</body>
</html>