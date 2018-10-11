<%@ page language="java" import="java.util.*,mybean.model.*,db.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
request.setCharacterEncoding("UTF-8");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<base href="<%=basePath%>">
		<title>商品页面</title>

		<link href="AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css" />
		<link href="AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css" />
		<link href="basic/css/demo.css" rel="stylesheet" type="text/css" />
		<link type="text/css" href="css/optstyle.css" rel="stylesheet" />
		<link type="text/css" href="css/style.css" rel="stylesheet" />

		<script type="text/javascript" src="basic/js/jquery-1.7.min.js"></script>
		<script type="text/javascript" src="basic/js/quick_links.js"></script>

		<script type="text/javascript" src="AmazeUI-2.4.2/assets/js/amazeui.js"></script>
		<script type="text/javascript" src="js/jquery.imagezoom.min.js"></script>
		<script type="text/javascript" src="js/jquery.flexslider.js"></script>
		<script type="text/javascript" src="js/list.js"></script>

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
					<c:choose>
						<c:when test="${!empty user.getId() }">
							<div class="menu-hd MyShangcheng"><a href="person/information.jsp" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a></div>
						</c:when>
						<c:otherwise>
							<div class="menu-hd MyShangcheng"><a href="javascript:alert('请先登录')" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a></div>
						</c:otherwise>
					</c:choose>
						
					</div>
					<c:choose>
						<c:when test="${!empty user.getId() }">
						<div class="topMessage mini-cart">
							<div class="menu-hd"><a id="mc-menu-hd" href="ShopCart?userId=${user.getId() }&state=nopay" target="_top"><i class="am-icon-shopping-cart  am-icon-fw"></i><span>购物车</span><strong id="J_MiniCartNum" class="h">0</strong></a></div>
						</div>
						</c:when>
						<c:otherwise>
					<div class="topMessage mini-cart">
							<div class="menu-hd"><a id="mc-menu-hd" href="javascript:alert('请先登录')" target="_top"><i class="am-icon-shopping-cart  am-icon-fw"></i><span>购物车</span><strong id="J_MiniCartNum" class="h">0</strong></a></div>
					</div>
						</c:otherwise>
					</c:choose>
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
			<div class="listMain">
				<ol class="am-breadcrumb am-breadcrumb-slash">
					<li><a href="#">首页</a></li>
					<li class="am-active">内容</li>
				</ol>
				
				<!--放大镜-->

				<div class="item-inform">
					<div class="clearfixLeft" id="clearcontent">

						<div class="box">
							<div class="tb-booth tb-pic tb-s310">
								<a href=""><img src="${introGoods[0].getImgPath() }"  class="jqzoom" /></a>
							</div>
						</div>

						<div class="clear"></div>
					</div>

					<div class="clearfixRight">

						<!--规格属性-->
						<!--名称-->
						<div class="tb-detail-hd">
							<h1>${introGoods[0].getGoodsName() }</h1>
						</div>
						<div class="tb-detail-list">
							<!--价格-->
							<div class="tb-detail-price">
								<li class="price iteminfo_price">
									<dt>价格</dt>
									<dd><em>¥</em><b class="sys_item_price">${introGoods[0].getPrice() }</b>  </dd>                                 
								</li>
								<li class="price iteminfo_mktprice">
									<dt>销量</dt>
									<dd><b class="sys_item_mktprice">${introGoods[0].getSelesVolume() }</b></dd>
								</li>

								<div class="clear"></div>
							</div>




							<!--各种规格-->
							<dl class="iteminfo_parameter sys_item_specpara">
								<dd>
									<!--操作页面-->

									<div class="theme-popover-mask"></div>

									<div class="theme-popover">
										<div class="theme-popbod dform">
											<form class="theme-signin" name="loginform" action="SubmitOrder" method="post" onsubmit="return check(${user.getId()})">

												<div class="theme-signin-left">

													<div class="theme-options">
														<div class="cart-title number">数量</div>
														<dd>
															<input class="min am-btn" name="jian" type="button" value="-" />
															<input class="text_box" name="num" type="text" value="1" style="width:30px;" />
															<input class="add am-btn" name="jia" type="button" value="+" />
															<input type="hidden" value="${introGoods[0].getPrice() }" name="price">
															<input type="hidden" value="${introGoods[0].getGoodsName() }" name="goodsName">
															<input type="hidden" value="${introGoods[0].getImgPath() }" name="imgPath">
															<input type="hidden" value="${user.getId() }" name="userId">
															<input type="hidden" value="${introGoods[0].getId() }" name="goodsId">
															<input type="hidden" value="${introGoods[0].getGoodsDescribe() }" name="goodsDescribe">
															<span id="Stock" class="tb-hidden">库存<span class="stock">1000</span>件</span>
														</dd>

													</div>
													<div class="clear"></div>


												</div>
												<div class="pay">
													<li>
														<div class="clearfix tb-btn tb-btn-basket theme-login">
															<input type="submit" value="加入购物车">
														</div>
													</li>
												</div>
											</form>
										</div>
									</div>

								</dd>
							</dl>
							<div class="clear"></div>
							<!--活动	-->
							<div class="shopPromotion gold">
								<div class="hot">
									<dt class="tb-metatit">商品描述</dt>
									<div class="gold-list">
										<p>${introGoods[0].getGoodsDescribe() }</p>
									</div>
								</div>
								<div class="clear"></div>

							</div>
						</div>
					</div>

					<div class="clear"></div>

				</div>
		<div class="footer ">
			<div class="footer-hd ">
				<p>
					<a href="# ">商城首页</a>
				</p>
			</div>
			<div class="footer-bd ">
				<p>
					<em>© 2015-2025 Hengwang.com 版权所有</em>
				</p>
			</div>
		</div>
		<script type="text/javascript">
			$("input[type=button][name=jian]").click(function (){
				var get = $("input[type=text][name=num]");
				var num = get.val();
				if(num >=1){
				jian = num*1-1;
				get.val(jian);
				};
			});
			$("input[type=button][name=jia]").click(function (){
				var get = $("input[type=text][name=num]");
				var num = get.val();
				if(num <=1000){
				jia = num*1+1;
				get.val(jia);
				};
			});
			function check(userId) {
				if(userId==null){
					alert("请先登录！");
					return false;
				}else{
					return true;
				}							
			}
													</script>
	</body>

</html>
