<%@ page language="java" import="java.util.*,mybean.model.*,db.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=0">
		<base href="<%=basePath%>">
		<title>绑定手机</title>

		<link href="../AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="../AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">

		<link href="../css/personal.css" rel="stylesheet" type="text/css">
		<link href="../css/stepstyle.css" rel="stylesheet" type="text/css">

		<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
		<script src="../AmazeUI-2.4.2/assets/js/amazeui.js"></script>

	</head>

	<body>
		<!--头 -->
		<header>
			<article>
				<div class="mt-logo">
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
							<div class="menu-hd MyShangcheng"><a href="showAddress?userId=${user.getId() }" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a></div>
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
				</div>
			</article>
		</header>
		<div class="center">
			<div class="col-main">
				<div class="main-wrap">

					<div class="am-cf am-padding">
						<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">绑定手机</strong> / <small>Bind&nbsp;Phone</small></div>
					</div>
					<hr/>
					<!--进度条-->
					<div class="m-progress">
						<div class="m-progress-list">
							<span class="step-1 step">
                                <em class="u-progress-stage-bg"></em>
                                <i class="u-stage-icon-inner">1<em class="bg"></em></i>
                                <p class="stage-name">绑定手机</p>
                            </span>
							<span class="step-2 step">
                                <em class="u-progress-stage-bg"></em>
                                <i class="u-stage-icon-inner">2<em class="bg"></em></i>
                                <p class="stage-name">完成</p>
                            </span>
							<span class="u-progress-placeholder"></span>
						</div>
						<div class="u-progress-bar total-steps-2">
							<div class="u-progress-bar-inner"></div>
						</div>
					</div>
					<form class="am-form am-form-horizontal">
						<div class="am-form-group bind">
							<label for="user-phone" class="am-form-label">验证手机</label>
							<div class="am-form-content">
								<span id="user-phone">186XXXX0531</span>
							</div>
						</div>
						<div class="am-form-group code">
							<label for="user-code" class="am-form-label">验证码</label>
							<div class="am-form-content">
								<input type="tel" id="user-code" placeholder="短信验证码" name="">
							</div>
							<a class="btn" href="javascript:void(0);" onclick="sendMobileCode();" id="sendMobileCode">
								<div class="am-btn am-btn-danger">验证码</div>
							</a>
						</div>
						<div class="am-form-group">
							<label for="user-new-phone" class="am-form-label">验证手机</label>
							<div class="am-form-content">
								<input type="tel" id="user-new-phone" placeholder="绑定新手机号" name="">
							</div>
						</div>
						<div class="am-form-group code">
							<label for="user-new-code" class="am-form-label">验证码</label>
							<div class="am-form-content">
								<input type="tel" id="user-new-code" placeholder="短信验证码" name="">
							</div>
							<a class="btn" href="javascript:void(0);" onclick="sendMobileCode();" id="sendMobileCode">
								<div class="am-btn am-btn-danger">验证码</div>
							</a>
						</div>
						<div class="info-btn">
							<input class="am-btn am-btn-danger" value="保存修改" type="submit" name="">
						</div>

					</form>

				</div>
			</div>

			<aside class="menu">
				<ul>
					<li class="person">
						<span>个人中心</span>
					</li>
					<li class="person">
						<ul>
							<li> <a href="person/information.jsp">个人信息</a></li>
							<li> <a href="person/safety.jsp">安全设置</a></li>
							<li class="active"> <a href="showAddress?userId=${user.getId() }">收货地址</a></li>
						</ul>
					</li>
					<li class="person">
						<ul>
							<li><a href="person/order.jsp">订单管理</a></li>

						</ul>
					</li>
				</ul>

			</aside>
		</div>

	</body>

</html>
