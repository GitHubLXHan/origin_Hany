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
		<title>地址管理</title>

		<link href="AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">

		<link href="css/personal.css" rel="stylesheet" type="text/css">
		<link href="css/addstyle.css" rel="stylesheet" type="text/css">
		<script src="AmazeUI-2.4.2/assets/js/jquery.min.js" type="text/javascript"></script>
		<script src="AmazeUI-2.4.2/assets/js/amazeui.js"></script>

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

					<div class="user-address">
						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">地址管理</strong> / <small>Address&nbsp;list</small></div>
						</div>
						<hr/>
						<ul class="am-avg-sm-1 am-avg-md-3 am-thumbnails">
						<c:forEach items="${allAddress }" var="st">
							<li style="border: 1px solid #ccffcd">
								<p class="new-tit new-p-re">
									<span class="new-txt">${st.getTakeName() }</span>
									<span class="new-txt-rd2">${st.getPhone() }</span>
								</p>
								<div class="new-mu_l2a new-p-re">
									<p class="new-mu_l2cw">
										<span class="title">地址：</span>
										<span class="province">${st.getProvince()}</span>
										<span class="city">${st.getCity() }</span>
										<span class="dist">${st.getTakeArea() }</span>
										<span class="street">${st.getDetailed() }</span></p>
								</div>
								<div class="new-addr-btn">
									<a href="DeleteAddress?userId=${user.getId() }&name=${st.getTakeName()}&phone=${st.getPhone()}" ><i class="am-icon-trash"></i>删除</a>
								</div>
							</li>
						</c:forEach>
						</ul>
						<div class="clear"></div>
						<a class="new-abtn-type" data-am-modal="{target: '#doc-modal-1', closeViaDimmer: 0}">添加新地址</a>
						<!--例子-->
						<div class="am-modal am-modal-no-btn" id="doc-modal-1">

							<div class="add-dress">

								<!--标题 -->
								<div class="am-cf am-padding">
									<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">新增地址</strong> / <small>Add&nbsp;address</small></div>
								</div>
								<hr/>

								<div class="am-u-md-12 am-u-lg-8" style="margin-top: 20px;">
								<c:choose>
									<c:when test="${addressNum>=3 }">
										<p>只能添加三个地址</p>
									</c:when>
									<c:otherwise>
										<form class="am-form am-form-horizontal" method="post" action="SaveAddress">

										<div class="am-form-group">
											<label for="user-name" class="am-form-label">收货人</label>
											<div class="am-form-content">
												<input type="text" id="user-name" placeholder="收货人" name="takeName">
											</div>
										</div>

										<div class="am-form-group">
											<label for="user-phone" class="am-form-label">手机号码</label>
											<div class="am-form-content">
												<input id="user-phone" placeholder="手机号必填" type="text" name="phone">
											</div>
										</div>
										<div class="am-form-group">
											<label for="user-address" class="am-form-label">所在地</label>
											<div class="am-form-content address">
												<select data-am-selected name="province">
													<option value="浙江省">浙江省</option>
													<option value="湖北省" selected>湖北省</option>
												</select>
												<select data-am-selected name="city">
													<option value="温州市">温州市</option>
													<option value="武汉市" selected>武汉市</option>
												</select>
												<select data-am-selected name="takeArea">
													<option value="瑞安区">瑞安区</option>
													<option value="洪山区" selected>洪山区</option>
												</select>
											</div>
										</div>

										<div class="am-form-group">
											<label for="user-intro" class="am-form-label">详细地址</label>
											<div class="am-form-content">
												<textarea class="" rows="3" id="user-intro" placeholder="输入详细地址" name="detailed"></textarea>
												<small>100字以内写出你的详细地址...</small>
											</div>
										</div>
						
										<div class="am-form-group">
											<div class="am-u-sm-9 am-u-sm-push-3">
												<input type="hidden" value="${user.getId() }" name="userId">
												<input class="am-btn am-btn-danger" type="submit" value="保存">
												<input href="javascript: void(0)" class="am-close am-btn am-btn-danger" data-am-modal-close type="reset" value="取消">
											</div>
										</div>
									</form>									
									</c:otherwise>
								</c:choose>
									
								</div>

							</div>

						</div>

					</div>

					<script type="text/javascript">
						$(document).ready(function() {
							$(".new-option-r").click(function() {
								$(this).parent('.user-addresslist').addClass("defaultAddr").siblings().removeClass("defaultAddr");
							});

							var $ww = $(window).width();
							if($ww>640) {
								$("#doc-modal-1").removeClass("am-modal am-modal-no-btn")
							}

						})
					</script>

					<div class="clear"></div>

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