学号：202230442173
姓名：陆泳谷

webapps文件夹：
1.admin：管理员页面的.jsp,.css文件，images：存放前端添加的商品图片。
2.client:客户页面的.jsp,.css文件，images：存放首页显示图标



java->com.exanple->controller:
    存放servelt文件：
        admin相关：AdminInServlet.java 管理员登陆
                  AdminProduct.java 进入商品管理页面及展示
                  AddProduct.java：添加商品
                  UpdCartServlet.java 修改商品信息
                  DeleteProduct.java 删除商品
                  AdminClient.java：客户管理
                  LogServlet.java 客户活动日志
                  OrderServlet.java 订单管理
                  OrderDetail.java订单详情页
                  StatisticsServlet.java 销售统计分析
        
        client相关：ProductServlet.java 首页展示商品
                   RegisterServlet.java 注册
                   LoginServlet.java 登陆
                   LogoutServlet.java 注销
                   DetailServlet.java商品详情展示
                   AddToCartServlet.java 加入购物车
                   CartServlet.java 购物车显示
                   CheckoutServlet.java购物车结算功能
                   MyOrder.java 我的订单查看
                   MyOrdetail.java订单详情页
     ShowIndexServlet.java将项目默认进入页面转发
