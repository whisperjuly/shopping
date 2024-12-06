<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="homepage.jsp" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/client/css/homepage.css">
<div class="product">
            <div  id="digital-products">
                <h2>数码产品</h2>
                   <div class="product-display">
                   <c:set var="countDigital" value="0" scope="page" />
                       <c:forEach var="product" items="${products}" >
                           <c:if test="${product.category eq 'digital-products'}">
                              <c:if test="${countDigital < 4}">
                                <div class="product-item"
                                onclick="window.location.href='<c:url value="/Detail?id=${product.id}" />'">
                                    <div class="img-wrapper">
                                        <img src="<c:url value='/${product.imgUrl}' />" alt="Product Image">
                                    </div>
                                    <div class="product-description">
                                        <p class="product-name">${product.description}</p>
                                        <p class="product-price">￥${product.price}</p>
                                    </div>
                                </div>
                                </c:if>
                            </c:if>
                       </c:forEach>
                   </div>
            </div>
            <div id="fashion">
                <h2>服饰美容</h2>
                <div class="product-display">
                    <c:set var="countfa" value="0" scope="page" />
                        <c:forEach var="product" items="${products}" >
                            <c:if test="${product.category eq 'fashion'}">
                               <c:if test="${countfa < 4}">
                                <div class="product-item"
                                onclick="window.location.href='<c:url value="/Detail?id=${product.id}" />'">
                                    <div class="img-wrapper">
                                        <img src="<c:url value='/${product.imgUrl}' />" alt="Product Image">
                                    </div>
                                    <div class="product-description">
                                        <p class="product-name">${product.description}</p>
                                        <p class="product-price">￥${product.price}</p>
                                    </div>
                                </div>
                                </c:if>
                            </c:if>
                       </c:forEach>
                   </div>
            </div>
            <div id="snacks">
                <h2>零食生鲜</h2>
                   <div class="product-display">
                    <c:set var="countfa" value="0" scope="page" />
                        <c:forEach var="product" items="${products}" >
                            <c:if test="${product.category eq 'snacks'}">
                                <c:if test="${countfa < 4}">
                                <div class="product-item"
                                onclick="window.location.href='<c:url value="/Detail?id=${product.id}" />'">
                                    <div class="img-wrapper">
                                        <img src="<c:url value='/${product.imgUrl}' />" alt="Product Image">
                                    </div>
                                    <div class="product-description">
                                        <p class="product-name">${product.description}</p>
                                        <p class="product-price">￥${product.price}</p>
                                    </div>
                                </div>
                                </c:if>
                            </c:if>
                       </c:forEach>
                   </div>
            </div>
            <div id="furniture">
                <h2>家具用品</h2>
                   <div class="product-display">
                    <c:set var="countfa" value="0" scope="page" />
                        <c:forEach var="product" items="${products}" >
                            <c:if test="${product.category eq 'furniture'}">
                                <c:if test="${countfa < 4}">
                                <div class="product-item"
                                onclick="window.location.href='<c:url value="/Detail?id=${product.id}" />'">
                                    <div class="img-wrapper">
                                        <img src="<c:url value='/${product.imgUrl}' />" alt="Product Image">
                                    </div>
                                    <div class="product-description">
                                        <p class="product-name">${product.description}</p>
                                        <p class="product-price">￥${product.price}</p>
                                    </div>
                                </div>
                                </c:if>
                            </c:if>
                       </c:forEach>
                   </div>
            </div>
            <div id="book-fun">
                <h2>娱乐图书</h2>
                   <div class="product-display">
                     <c:set var="countfa" value="0" scope="page" />
                         <c:forEach var="product" items="${products}" >
                             <c:if test="${product.category eq 'book-fun'}">
                                <c:if test="${countfa < 4}">
                                <div class="product-item"
                                onclick="window.location.href='<c:url value="/Detail?id=${product.id}" />'">
                                    <div class="img-wrapper">
                                        <img src="<c:url value='/${product.imgUrl}' />" alt="Product Image">
                                    </div>
                                    <div class="product-description">
                                        <p class="product-name">${product.description}</p>
                                        <p class="product-price">￥${product.price}</p>
                                    </div>
                                </div>
                                </c:if>
                            </c:if>
                       </c:forEach>
                   </div>
            </div>

 </div>