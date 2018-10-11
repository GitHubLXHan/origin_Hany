<%@ page language="java" import="java.util.*,mybean.model.*,db.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
request.setCharacterEncoding("UTF-8");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
		<base href="<%=basePath%>">
		<title>全部商品</title>

		<link href="AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css" />
		<link href="AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css" />

		<link href="basic/css/demo.css" rel="stylesheet" type="text/css" />

		<link href="css/seastyle.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="basic/js/jquery-1.7.min.js"></script>
		<script type="text/javascript" src="js/script.js"></script>
	</head>

	<body>

		<!--顶部导航条 -->
		<div class="am-container header">
			<ul class="message-l">
				<div class="topMessage">
					<div class="menu-hd">
						<c:choose>
        					<c:when test="${empty user}">
        						<a href="home/login.jsp" target="_top">亲，请登录</a>
        					</c:when>
        				<c:otherwise>
        		    		<a href="#" target="_top">${user.getNickname()}</a>
        					<a href="logout" target="_top" onclick="javascript:return window.confirm('确认注销吗？');">注销</a>
        				</c:otherwise>
        				</c:choose>
        				<a href="home/register.jsp" target="_top">免费注册</a>
					</div>
				</div>
			</ul>
			<ul class="message-r">
				<div class="topMessage home">
					<div class="menu-hd"><a href="Home" target="_top" class="h">商城首页</a></div>
				</div>
				<div class="topMessage my-shangcheng">
					<div class="menu-hd MyShangcheng"><a href="person/information.jsp" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a></div>
				</div>
				<div class="topMessage mini-cart">
					<div class="menu-hd"><a id="mc-menu-hd" href="#" target="_top"><i class="am-icon-shopping-cart  am-icon-fw"></i><span>购物车</span><strong id="J_MiniCartNum" class="h">0</strong></a></div>
				</div>
			</ul>
			</div>

			<!--悬浮搜索框-->

		<div class="nav white">
			<div class="logo"><img src="images/logo.png" /></div>
			<div class="logoBig">
				<li><img src="images/logobig.png" /></li>
			</div>

			<div class="search-bar pr">
				<a name="index_none_header_sysc" href="#"></a>
				<form method="get" action="Search">
					<input id="searchInput" name="keyWord" type="text" placeholder="搜索" autocomplete="off"/>
					<input id="ai-topsearch" class="submit am-btn"  value="搜索" index="1" type="submit"/>
				</form>
			</div>
		</div>

			<div class="clear"></div>
			<b class="line"></b>
           <div class="search">
			<div class="search-list">
					<div class="am-g am-g-fixed">
						<div class="am-u-sm-12 am-u-md-12">
							<div class="search-content">
								<div class="sort">
									<li class="first"><a title="全部商品">全部商品</a></li>
								</div>
								<div class="clear"></div>

								<ul class="am-avg-sm-2 am-avg-md-3 am-avg-lg-4 boxes">
								<c:forEach items="${allGoods }" var="st">
									<li>
										<div class="i-pic limit">
											<a href="Introduction?id=${st.getId() }"><img src="${st.getImgPath() }" /></a>											
											<p class="title fl">${st.getGoodsDescribe() }</p>
											<p class="price fl">
												<b>¥</b>
												<strong>${st.getPrice() }</strong>
											</p>
											<p class="number fl">
												销量<span>${st.getSelesVolume() }</span>
											</p>
										</div>
									</li>
								</c:forEach>

								</ul>
							</div>
							<div class="clear"></div>
							<!--分页 -->
							<ul class="am-pagination am-pagination-right">
							<c:choose>
								<c:when test="${page<=1}">
    								<li class="am-disabled"><a href="AllGoods?page=${page-1}">&laquo;</a></li>
    							</c:when>
    							<c:otherwise>
    								<li><a href="AllGoods?page=${page-1}">&laquo;</a></li>
    							</c:otherwise>
							</c:choose>
							<c:forEach var="i" begin="1" end="${pageCount }">
								<c:choose>
									<c:when test="${i==page }">
										<li class="am-active"><a href="AllGoods?page=${page}">${i }</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="AllGoods?page=${i}">${i }</a></li>
									</c:otherwise>
								</c:choose>				
							</c:forEach>
							<c:choose>
								<c:when test="${page>=pageCount}">
    								<li class="am-disabled"><a href="AllGoods?page=${page+1}">&raquo;</a></li>
    							</c:when>
    							<c:otherwise>
    								<li><a href="AllGoods?page=${page+1}">&raquo;</a></li>
    							</c:otherwise>
							</c:choose>
							</ul>

						</div>
					</div>
					<div class="footer">
						<div class="footer-hd">
							<p>
								<a href="#">商城首页</a>
							</p>
						</div>
						<div class="footer-bd">
							<p>
								<em>© 2015-2025 Hengwang.com 版权所有</em>
							</p>
						</div>
					</div>
				</div>

			</div>
		<script>
			window.jQuery || document.write('<script src="basic/js/jquery-1.9.min.js"><\/script>');
		</script>
		<script type="text/javascript" src="../basic/js/quick_links.js"></script>

<div class="theme-popover-mask"></div>

	</body>

</html>
