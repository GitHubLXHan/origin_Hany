<%@ page language="java" import="java.util.*,mybean.model.*,db.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

		<title>首页</title>

		<link href="AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css" />
		<link href="AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css" />

		<link href="basic/css/demo.css" rel="stylesheet" type="text/css" />

		<link href="css/hmstyle.css" rel="stylesheet" type="text/css" />
        <link href="css/imitation.css" rel="stylesheet" type="text/css" />
		<script src="AmazeUI-2.4.2/assets/js/jquery.min.js"></script>
		<script src="AmazeUI-2.4.2/assets/js/amazeui.min.js"></script>
        <script type="text/javascript" src="basic/js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="js/jquery.hoverex.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
    </head>

	<body>
		<div class="hmtop">
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
					<input id="searchInput" name="keyWord" type="text" placeholder="搜索" autocomplete="off">
					<input id="ai-topsearch" class="submit am-btn"  value="搜索" index="1" type="submit">
				</form>
			</div>
		</div>
				<div class="clear"></div>
			</div>

			
			<div class="banner">
                      <!--轮播 -->
						<div class="am-slider am-slider-default scoll" data-am-flexslider id="demo-slider-0">
							<ul class="am-slides">
								<li class="banner1"><a href="introduction.html"><img src="images/ad1.jpg" /></a></li>
								<li class="banner2"><a><img src="images/ad2.jpg" /></a></li>
								<li class="banner3"><a><img src="images/ad3.jpg" /></a></li>
								<li class="banner4"><a><img src="images/ad4.jpg" /></a></li>

							</ul>
						</div>
						<div class="clear"></div>	
			</div>						
			
			<div class="shopNav">
				<div class="slideall">
			        
					   <div class="long-title"><span class="all-goods">全部分类</span></div>
					   <div class="nav-cont">
							<ul>
								<li class="index"><a href="AllGoods">全部商品</a></li>
                                <li class="qc"><a href="#">闪购</a></li>
                                <li class="qc"><a href="#">限时抢</a></li>
                                <li class="qc"><a href="#">团购</a></li>
                                <li class="qc last"><a href="#">大包装</a></li>
							</ul>
						</div>
		        				
						<!--侧边导航 -->
						<div id="nav" class="navfull">
							<div class="area clearfix">
								<div class="category-content" id="guide_2">
									
									<div class="category">
										<ul class="category-list" id="js_climit_li">
											<li class="appliance js_toggle relative first">
												<div class="category-info">
													<h3 class="category-name b-category-name"><i><img src="images/cake.png"></i><a class="ml-22" title="保温餐具">保温餐具</a></h3>
													<em>&gt;</em></div>
												<div class="menu-item menu-in top">
													<div class="area-in">
														<div class="area-bg">
															<div class="menu-srot">
																<div class="sort-side">
																	<dl class="dl-sort">
																		<dt><span title="材质">材质</span></dt>
																		<dd><a title="304不锈钢" href="#"><span>304不锈钢</span></a></dd>
																		<dd><a title="普通不锈钢" href="#"><span>普通不锈钢</span></a></dd>
																	</dl>
																	<dl class="dl-sort">
																		<dt><span title="适用人群">适用人群</span></dt>
																		<dd><a title="成年人" href="#"><span>成年人</span></a></dd>
																		<dd><a title="儿童" href="#"><span>儿童</span></a></dd>
																	</dl>
																</div>
															</div>
														</div>
													</div>
												</div>
											<b class="arrow"></b>	
											</li>
											<li class="appliance js_toggle relative">
												<div class="category-info">
													<h3 class="category-name b-category-name"><i><img src="images/cookies.png"></i><a class="ml-22" title="保温壶">保温壶</a></h3>
													<em>&gt;</em></div>
												<div class="menu-item menu-in top">
													<div class="area-in">
														<div class="area-bg">
															<div class="menu-srot">
																<div class="sort-side">
																	<dl class="dl-sort">
																		<dt><span title="材质">材质</span></dt>
																		<dd><a title="304不锈钢" href="#"><span>304不锈钢</span></a></dd>
																		<dd><a title="普通不锈钢" href="#"><span>普通不锈钢</span></a></dd>
																	</dl>
																	<dl class="dl-sort">
																		<dt><span title="适用人群">薯片</span></dt>
																		<dd><a title="成年人" href="#"><span>成年人</span></a></dd>
																		<dd><a title="儿童" href="#"><span>儿童</span></a></dd>
																	</dl>
																</div>
															</div>
														</div>
													</div>
												</div>
                                             <b class="arrow"></b>
											</li>
											<li class="appliance js_toggle relative">
												<div class="category-info">
													<h3 class="category-name b-category-name"><i><img src="images/meat.png"></i><a class="ml-22" title="保温杯">保温杯</a></h3>
													<em>&gt;</em></div>
												<div class="menu-item menu-in top">
													<div class="area-in">
														<div class="area-bg">
															<div class="menu-srot">
																<div class="sort-side">
																	<dl class="dl-sort">
																		<dt><span title="颜色">颜色</span></dt>
																		<dd><a title="红色" href="#"><span>红色</span></a></dd>
																		<dd><a title="银色" href="#"><span>银色</span></a></dd>
																		<dd><a title="黑色" href="#"><span>蓝色</span></a></dd>
																		<dd><a title="黑色" href="#"><span>黑色</span></a></dd>
																	</dl>
																	<dl class="dl-sort">
																		<dt><span title="适用人群">适用人群</span></dt>
																		<dd><a title="成年人" href="#"><span>成年人</span></a></dd>
																		<dd><a title="儿童" href="#"><span>儿童</span></a></dd>
																	</dl>
																</div>
															</div>
														</div>
													</div>
												</div>
                                            <b class="arrow"></b>
											</li>
											<li class="appliance js_toggle relative">
												<div class="category-info">
													<h3 class="category-name b-category-name"><i><img src="images/bamboo.png"></i><a class="ml-22" title="餐具类">餐具类</a></h3>
													<em>&gt;</em></div>
												<div class="menu-item menu-in top">
													<div class="area-in">
														<div class="area-bg">
															<div class="menu-srot">
																<div class="sort-side">
																	<dl class="dl-sort">
																		<dt><span title="材质">材质</span></dt>
																		<dd><a title="304不锈钢" href="#"><span>304不锈钢</span></a></dd>
																		<dd><a title="普通不锈钢" href="#"><span>普通不锈钢</span></a></dd>
																	</dl>
																	<dl class="dl-sort">
																		<dt><span title="风格">风格</span></dt>
																		<dd><a title="礼盒款" href="#"><span>礼盒款</span></a></dd>
																	</dl>
																</div>
															</div>
														</div>
													</div>
												</div>
                                             <b class="arrow"></b>
											</li>
											<li class="appliance js_toggle relative">
												<div class="category-info">
													<h3 class="category-name b-category-name"><i><img src="images/nut.png"></i><a class="ml-22" title="杯碗系列">杯碗系列</a></h3>
													<em>&gt;</em></div>
												<div class="menu-item menu-in top">
													<div class="area-in">
														<div class="area-bg">
															<div class="menu-srot">
																<div class="sort-side">
																	<dl class="dl-sort">
																		<dt><span title="材质">材质</span></dt>
																		<dd><a title="陶瓷" href="#"><span>陶瓷</span></a></dd>
																		<dd><a title="瓷" href="#"><span>瓷</span></a></dd>
																	</dl>
																	<dl class="dl-sort">
																		<dt><span title="颜色分类">颜色分类</span></dt>
																		<dd><a title="粉红色" href="#"><span>粉红色</span></a></dd>
																		<dd><a title="天蓝色" href="#"><span>天蓝色</span></a></dd>
																		<dd><a title="乳白色" href="#"><span>乳白色</span></a></dd>
																		<dd><a title="浅灰色" href="#"><span>浅灰色</span></a></dd>
																		<dd><a title="深灰色" href="#"><span>深灰色</span></a></dd>
																	</dl>
																</div>
															</div>
														</div>
													</div>
												</div>
												<b class="arrow"></b>
											</li>
										</ul>
									</div>
								</div>

							</div>
						</div>
						<!--轮播 -->
						<script type="text/javascript">
							(function() {
								$('.am-slider').flexslider();
							});
							$(document).ready(function() {
								$("li").hover(function() {
									$(".category-content .category-list li.first .menu-in").css("display", "none");
									$(".category-content .category-list li.first").removeClass("hover");
									$(this).addClass("hover");
									$(this).children("div.menu-in").css("display", "block")
								}, function() {
									$(this).removeClass("hover")
									$(this).children("div.menu-in").css("display", "none")
								});
							})
						</script>

				</div>
				<script type="text/javascript">
					if ($(window).width() < 640) {
						function autoScroll(obj) {
							$(obj).find("ul").animate({
								marginTop: "-39px"
							}, 500, function() {
								$(this).css({
									marginTop: "0px"
								}).find("li:first").appendTo(this);
							})
						}
						$(function() {
							setInterval('autoScroll(".demo")', 3000);
						})
					}
				</script>
			</div>
			<div class="shopMainbg">
				<div class="shopMain" id="shopmain">

					<!--衣服 -->

					<div class="am-g am-g-fixed recommendation">
						<div class="clock am-u-sm-3" >
							<img src="images/2016.png "/>
							<p>今年<br/>新款</p>
						</div>
						<div class="am-u-sm-4 am-u-lg-3 ">
							<div class="info ">
								<h3>韩版短袖T</h3>
								<h4>男士修身印花短袖T恤男</h4>
							</div>
							<div class="recommendationMain ">
								<img src="img/c8.jpg"/>
							</div>
						</div>						
						<div class="am-u-sm-4 am-u-lg-3 ">
							<div class="info ">
								<h3>打底衫女长袖</h3>
								<h4>纯棉打底衫女长袖中长款修身加绒加厚</h4>
							</div>
							<div class="recommendationMain ">
								<img src="img/c9.jpg"/>
							</div>
						</div>
						<div class="am-u-sm-4 am-u-lg-3 ">
							<div class="info ">
								<h3>打底衫长袖女t</h3>
								<h4>加绒加厚上衣秋装韩版修身高领</h4>
							</div>
							<div class="recommendationMain ">
								<img src="img/c10.jpg"/>
							</div>
						</div>

					</div>
					<div class="clear "></div>
					<!--零食 -->
				<div class="am-container ">
						<div class="shopTitle ">
							<h4>零食</h4>
							<h3>多种多样 任您选购</h3>
						</div>

                        <div class="wapper">
                            <ul>
							<c:forEach items="${goods}" var="st" begin="0" end="2">    
							<a href="Introduction?id=${st.getId() }">
                                <li>
                                    <div class="pic"><img src="${st.getImgPath() }" width="160" height="160" /></div>
                                    <h3 class="title"><a href="#">${st.getGoodsName() }</a></h3>
                                    <p class="desc">${st.getGoodsDescribe() }</p>
                                    <p class="price"> <span class="num">${st.getPrice() }</span>元 </p>
                                </li>
                            </a>    
                            </c:forEach>
                            </ul>
                        </div>
					</div>
					<div class="clear "></div>

					<!--家电-->
					<div class="am-container ">
						<div class="shopTitle ">
							<h4>家电</h4>
							<h3>专卖大小家电，乐享万户千家</h3>
						</div>
		                <div class="demowrap cf">
		                    <c:forEach items="${goods }" var="st" begin="3" end="5">
		                    <div class="box">
		                        <div class="he-wrap tpl5">
		                            <img src="${st.getImgPath() }" alt="" />
		                            <div class="he-view">
		                                <a href="Introduction?id=${st.getId() }" class="buy a0" data-animate="jellyInDown">购买</a>
		                                <a class="price a1" data-animate="jellyInDown" href="#">$${st.getPrice() }</a>
		                            </div>
		                        </div>
		                    </div>
							</c:forEach>
		                </div>
					</div>

                <!--书籍-->
					<div class="am-container ">
						<div class="shopTitle ">
							<h4>书籍</h4>
							<h3>求知而来，载之而归</h3>
						</div>
					</div>
					<div class="am-g am-g-fixed flood method3 ">
						<ul class="am-thumbnails ">
						<c:forEach items="${goods }" var="st" begin="6" end="11">
							<li>
								<div class="list ">
									<a href="Introduction?id=${st.getId() }">
										<img src="${st.getImgPath() }" />
										<div class="pro-title ">${st.getGoodsName() }</div>
										<span class="e-price ">￥${st.getPrice() }</span>
									</a>
								</div>
							</li>
						</c:forEach>
						</ul>

					</div>
				</div>
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
		</div>
		</div>
		<!--引导 -->

		<div class="navCir">
			<li class="active"><a href="home3.html"><i class="am-icon-home "></i>首页</a></li>
			<li><a href="sort.html"><i class="am-icon-list"></i>分类</a></li>
			<li><a href="shopcart.html"><i class="am-icon-shopping-basket"></i>购物车</a></li>	
			<li><a href="../person/index.html"><i class="am-icon-user"></i>我的</a></li>					
		</div>

		<script>
			window.jQuery || document.write('<script src="basic/js/jquery.min.js "><\/script>');
		</script>
		<script type="text/javascript " src="basic/js/quick_links.js "></script>
	</body>

</html>