<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<div class="product">
            <div  id="digital-products">
                <h2>数码产品</h2>
                   <div class="product-display">
                       <div class="product-item">
                           <div class="img-wrapper" style="background-image: url('client/images/huawei.png');">
                           </div>
                           <div class="product-description">
                               <p class="product-name">华为P50 1111111超大光圈柔光摄影超大内存，双卡双待</p>
                               <p class="product-price">￥100</p>
                           </div>
                       </div>
                       <div class="product-item">
                           <div class="img-wrapper" style="background-image: url('');">
                           </div>
                           <div class="product-description">
                               <p class="product-name">商品名称2</p>
                               <p class="product-price">￥200</p>
                           </div>
                       </div>
                       <div class="product-item">
                           <div class="img-wrapper" style="background-image: url('');">
                           </div>
                            <div class="product-description">
                                <p class="product-name">商品名称3</p>
                                <p class="product-price">￥200</p>
                            </div>
                       </div>
                       <div class="product-item">
                            <div class="img-wrapper" style="background-image: url('');">
                            </div>
                            <div class="product-description">
                                 <p class="product-name">商品名称4</p>
                                 <p class="product-price">￥200</p>
                            </div>
                       </div>

                   </div>
            </div>
            <div id="fashion">
                <h2>服饰美容</h2>
                   <div class="product-display">
                       <div class="product-item">
                           <div class="img-wrapper" style="background-image: url('client/images/huawei.png');">
                           </div>
                           <div class="product-description">
                               <p class="product-name">华为P50 1111111超大光圈柔光摄影超大内存，双卡双待</p>
                               <p class="product-price">￥100</p>
                           </div>
                       </div>
                       <div class="product-item">
                           <div class="img-wrapper" style="background-image: url('');">
                           </div>
                           <div class="product-description">
                               <p class="product-name">商品名称2</p>
                               <p class="product-price">￥200</p>
                           </div>
                       </div>
                       <div class="product-item">
                           <div class="img-wrapper" style="background-image: url('');">
                           </div>
                            <div class="product-description">
                                <p class="product-name">商品名称3</p>
                                <p class="product-price">￥200</p>
                            </div>
                       </div>
                       <div class="product-item">
                            <div class="img-wrapper" style="background-image: url('');">
                            </div>
                            <div class="product-description">
                                 <p class="product-name">商品名称4</p>
                                 <p class="product-price">￥200</p>
                            </div>
                       </div>
                   </div>
            </div>
            <div id="snacks">
                <h2>零食生鲜</h2>
                   <div class="product-display">
                       <div class="product-item">
                           <div class="img-wrapper" style="background-image: url('client/images/huawei.png');">
                           </div>
                           <div class="product-description">
                               <p class="product-name">华为P50 1111111超大光圈柔光摄影超大内存，双卡双待</p>
                               <p class="product-price">￥100</p>
                           </div>
                       </div>
                       <div class="product-item">
                           <div class="img-wrapper" style="background-image: url('');">
                           </div>
                           <div class="product-description">
                               <p class="product-name">商品名称2</p>
                               <p class="product-price">￥200</p>
                           </div>
                       </div>
                       <div class="product-item">
                           <div class="img-wrapper" style="background-image: url('');">
                           </div>
                            <div class="product-description">
                                <p class="product-name">商品名称3</p>
                                <p class="product-price">￥200</p>
                            </div>
                       </div>
                       <div class="product-item">
                            <div class="img-wrapper" style="background-image: url('');">
                            </div>
                            <div class="product-description">
                                 <p class="product-name">商品名称4</p>
                                 <p class="product-price">￥200</p>
                            </div>
                       </div>
                   </div>
            </div>
            <div id="furniture">
                <h2>家具用品</h2>
                   <div class="product-display">
                       <div class="product-item">
                           <div class="img-wrapper" style="background-image: url('<%= request.getContextPath() %>/client/images/huawei.png');">
                           </div>
                           <div class="product-description">
                               <p class="product-name">华为P50 1111111超大光圈柔光摄影超大内存，双卡双待</p>
                               <p class="product-price">￥100</p>
                           </div>
                       </div>
                       <div class="product-item">
                           <div class="img-wrapper" style="background-image: url('');">
                           </div>
                           <div class="product-description">
                               <p class="product-name">商品名称2</p>
                               <p class="product-price">￥200</p>
                           </div>
                       </div>
                       <div class="product-item">
                           <div class="img-wrapper" style="background-image: url('');">
                           </div>
                            <div class="product-description">
                                <p class="product-name">商品名称3</p>
                                <p class="product-price">￥200</p>
                            </div>
                       </div>
                       <div class="product-item">
                            <div class="img-wrapper" style="background-image: url('');">
                            </div>
                            <div class="product-description">
                                 <p class="product-name">商品名称4</p>
                                 <p class="product-price">￥200</p>
                            </div>
                       </div>
                   </div>
            </div>
 </div>



<c:if test="${product.category} == 'digital-products' && status.index < 4">
    <div class="product-item">
        <div class="img-wrapper" style="background-image: url('${product.imgUrl}');">
        </div>
        <div class="product-description">
            <p class="product-name">${product.description}</p>
            <p class="product-price">￥${product.price}</p>
        </div>
    </div>
</c:if>