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
		<title>个人资料</title>

		<link href="AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">

		<link href="css/personal.css" rel="stylesheet" type="text/css">
		<link href="css/infstyle.css" rel="stylesheet" type="text/css">
		<script src="AmazeUI-2.4.2/assets/js/jquery.min.js" type="text/javascript"></script>
		<script src="AmazeUI-2.4.2/assets/js/amazeui.js" type="text/javascript"></script>
			
	</head>

	<body>
		<!--头 -->
		<header>
			<article>
				<div class="mt-logo">
	<!--顶部导航条 -->
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

					<div class="user-info">
						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">个人资料</strong> / <small>Personal&nbsp;information</small></div>
						</div>
						<hr/>

						<!--头像 -->
						<div class="user-infoPic">

							<div class="filePic">
								<input type="file" class="inputPic" allowexts="gif,jpeg,jpg,png,bmp" accept="image/*">
								<img class="am-circle am-img-thumbnail" src="${user.getImgPath() }" alt="" />
							</div>

							<p class="am-form-help">头像</p>

							<div class="info-m">
								<div><b>用户名：<i>${user.getNickname() }</i></b></div>
							</div>
						</div>

						<!--个人信息 -->
						<div class="info-main">
							<form class="am-form am-form-horizontal" method="post" action="ChangeInfo">

								<div class="am-form-group">
									<label for="user-name2" class="am-form-label">昵称</label>
									<div class="am-form-content">
										<input type="text" id="user-name2" placeholder="nickname" value="${user.getNickname() }" name="nickname">
										
									</div>
								</div>

								<div class="am-form-group">
									<label for="user-name" class="am-form-label">姓名</label>
									<div class="am-form-content">
										<input type="text" id="user-name2" placeholder="name" value="${user.getUsername() }" name="name">
										
									</div>
								</div>

								<div class="am-form-group">
									<label class="am-form-label">性别</label>
									<div class="am-form-content sex">
									<c:choose>
										<c:when test="${user.getSex()=='男' }">
											<label class="am-radio-inline">
												<input type="radio" name="radio10" checked value="男" data-am-ucheck/> 男
											</label>
											<label class="am-radio-inline">
												<input type="radio" name="radio10" value="女" data-am-ucheck/> 女
											</label>
											<label class="am-radio-inline">
												<input type="radio" name="radio10" value="保密" data-am-ucheck/> 保密
											</label>
										</c:when>
										<c:when test="${user.getSex()=='女' }">
											<label class="am-radio-inline">
												<input type="radio" name="radio10" value="男" data-am-ucheck/> 男
											</label>
											<label class="am-radio-inline">
												<input type="radio" name="radio10" checked value="女" data-am-ucheck/> 女
											</label>
											<label class="am-radio-inline">
												<input type="radio" name="radio10" value="保密" data-am-ucheck/> 保密
											</label>
										</c:when>
										<c:when test="${user.getSex()=='保密' }">
											<label class="am-radio-inline">
												<input type="radio" name="radio10" value="男" data-am-ucheck/> 男
											</label>
											<label class="am-radio-inline">
												<input type="radio" name="radio10" value="女" data-am-ucheck/> 女
											</label>
											<label class="am-radio-inline">
												<input type="radio" name="radio10" checked value="保密" data-am-ucheck/> 保密
											</label>
										</c:when>
										<c:otherwise>
											<label class="am-radio-inline">
												<input type="radio" name="radio10" value="男" data-am-ucheck/> 男
											</label>
											<label class="am-radio-inline">
												<input type="radio" name="radio10" value="女" data-am-ucheck/> 女
											</label>
											<label class="am-radio-inline">
												<input type="radio" name="radio10" value="保密" data-am-ucheck/> 保密
											</label>
										</c:otherwise>
									</c:choose>
										
									</div>
								</div>

								<div class="am-form-group">
									<label for="user-birth" class="am-form-label">生日</label>
									<div class="am-form-content birth">
										<div class="birth-select">
											<select data-am-selected="{maxHeight: 100}" name="birthYear">
												<option value="${user.getBirthYear() }">${user.getBirthYear() }</option>
												<option value="1990">1990</option>
												<option value="1991">1991</option>
												<option value="1992">1992</option>
												<option value="1993">1993</option>
												<option value="1994">1994</option>
												<option value="1995">1995</option>
												<option value="1996">1996</option>
												<option value="1997">1997</option>
												<option value="1998">1998</option>
												<option value="1999">1999</option>
												<option value="2010">2010</option>
											</select>
											<em>年</em>
										</div>
										<div class="birth-select2">
											<select data-am-selected="{maxHeight: 100}" name="birthMonth">
												<option value="${user.getBirthMonth() }">${user.getBirthMonth() }</option>
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
												<option value="6">6</option>
												<option value="7">7</option>
												<option value="8">8</option>
												<option value="9">9</option>
												<option value="10">10</option>
												<option value="11">11</option>
												<option value="12">12</option>
											</select>
											<em>月</em></div>
										<div class="birth-select2">
											<select data-am-selected="{maxHeight: 100}" name="birthDay">
												<option value="${user.getBirthDay() }">${user.getBirthDay() }</option>
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
												<option value="6">6</option>
												<option value="7">7</option>
												<option value="8">8</option>
												<option value="9">9</option>
												<option value="10">10</option>
												<option value="11">11</option>
												<option value="12">12</option>
												<option value="13">13</option>
												<option value="14">14</option>
												<option value="15">15</option>
												<option value="16">16</option>
												<option value="17">17</option>
												<option value="18">18</option>
												<option value="19">19</option>
												<option value="20">20</option>
												<option value="21">21</option>
												<option value="22">22</option>
												<option value="23">23</option>
												<option value="24">24</option>
												<option value="25">25</option>
												<option value="26">26</option>
												<option value="27">27</option>
												<option value="28">28</option>
												<option value="29">29</option>
												<option value="30">30</option>
												<option value="31">31</option>
											</select>
											<em>日</em></div>
									</div>
							
								</div>
								<div class="am-form-group">
									<label for="user-phone" class="am-form-label">电话</label>
									<div class="am-form-content">
									<c:choose>
										<c:when test="${!empty user.getPhone() }">
											<input id="user-phone" placeholder="telephonenumber" readonly="readonly" name="phone" type="tel" value="${user.getPhone() }">
										</c:when>
										<c:otherwise>
											<input id="user-phone" placeholder="telephonenumber" type="tel" name="phone" value="${user.getPhone() }">
										</c:otherwise>
									</c:choose>
										
									</div>
								</div>
								<div class="am-form-group">
									<label for="user-email" class="am-form-label">电子邮件</label>
									<div class="am-form-content">
									<c:choose>
										<c:when test="${!empty user.getEmail() }">
											<input id="user-email" placeholder="Email" readonly="readonly" name="email" type="email" value="${user.getEmail() }"/>
										</c:when>
										<c:otherwise>
											<input id="user-email" placeholder="Email" type="email" name="email" value="${user.getEmail() }"/>
										</c:otherwise>
									</c:choose>
										
									</div>
								</div>
								<div class="info-btn">
									<input type="hidden" name="userId" value="${user.getId() }"/>
									<input class="am-btn am-btn-danger" value="保存修改" type="submit" name="">
								</div>
							</form>
						</div>

					</div>

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