<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="user" uri="/tlds/user.tld" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags" %>
<c:set var="APP_PATH" value="${pageContext.request.contextPath}"></c:set>
<!doctype html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>点餐系统</title>

<!-- Set render engine for 360 browser -->
<meta name="renderer" content="webkit">

<!-- No Baidu Siteapp-->
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="icon" type="image/png"
	href="${APP_PATH}/static/amazeui/i/favicon.png">

<!-- Add to homescreen for Chrome on Android -->
<meta name="mobile-web-app-capable" content="yes">
<link rel="icon" sizes="192x192"
	href="${APP_PATH}/static/amazeui/i/app-icon72x72@2x.png">

<!-- Add to homescreen for Safari on iOS -->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="apple-touch-icon-precomposed"
	href="${APP_PATH}/static/amazeui/i/app-icon72x72@2x.png">

<!-- Tile icon for Win8 (144x144 + tile color) -->
<meta name="msapplication-TileImage"
	content="${APP_PATH}/static/amazeui/i/app-icon72x72@2x.png">
<meta name="msapplication-TileColor" content="#0e90d2">

<link rel="stylesheet"
	href="${APP_PATH}/static/amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="${APP_PATH}/static/amazeui/css/app.css">
<script src="${APP_PATH}/static/jquery/jquery-1.8.3.min.js"></script>
<script src="${APP_PATH}/static/client/order/order.js"></script>
<script src="${APP_PATH}/static/utils/dateformat.js"></script>
<script src="${APP_PATH}/static/amazeui/js/amazeui.min.js"></script>
<style>
i{
	font-style:normal;
}
label{
	font-style: normal;
	margin-left: 16px;
	line-height: 20px;
	font-weight: normal;
}
</style>
</head>
<body>
	<!-- 页头 -->
	<header data-am-widget="header" class="am-header am-header-default">
		<h1 class="am-header-title">
			<a href="#title-link" class=""> 点餐系统 </a>
		</h1>
	</header>
	<div class="am-container" style="margin-top:16px">
		<span>
			<img class="am-img-thumbnail am-circle" src="/shitao/static/upload/heads/bw-2014-06-19.jpg" width="80" height="80"/>
			<label>
				<i>${username }</i><br/>
				<i>余额：</i><i>${money }</i>
			</label>
		</span>
	</div>
	
	<!-- 内容区域 -->
	<ul class="am-list am-list-border">
		<li><a href="${APP_PATH}/a/index/order"> <i class="am-icon-book am-icon-fw"></i>
				我的订单
		</a></li>
		<!-- 
		<li><a href="#"> <i class="am-icon-street-view am-icon-fw"></i>
				个人资料
		</a></li>-->
		<li><a href="javascript:logout();"> <i class="am-icon-reply am-icon-fw"></i>
				注销
		</a></li>
	</ul>
	
	<!-- 隐藏的上拉框 -->
	<order:order content="订单" type="订单"/>
	<!-- 页脚 -->
	<div data-am-widget="navbar" class="am-navbar am-cf am-navbar-default "
		id="">
		<ul class="am-navbar-nav am-cf am-avg-sm-4">
			<li><a href="${APP_PATH}/a/index/indexShow" class=""> <span
					class="am-icon-phone"></span> <span class="am-navbar-label">点餐</span>
			</a></li>
			<li><a href="${APP_PATH}/a/index/foodclassify" class=""> <span
					class="am-icon-bars"></span> <span class="am-navbar-label">分类</span>
			</a></li>
			<li id="dropDown"><a href="javascript:void(0);" class="">
				<span class="am-icon-cart-plus"></span>
				<span class="am-navbar-label">订单</span>
			</a></li>
			<li><a href="###" class=""> <span class="am-icon-user"></span>
					<span class="am-navbar-label">我的</span>
			</a></li>
		</ul>
	</div>
	<!--[if (gte IE 9)|!(IE)]><!-->
	<!--<![endif]-->
	<!--[if lte IE 8 ]>
    <script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
    <script src="assets/js/amazeui.ie8polyfill.min.js"></script>
    <![endif]-->
	<script type="text/javascript">
	$(document).ready(function(){
		$("#dropDown").click(function() {
			$modal = $("#my-actions");
			$modal.modal();
		});
		
		
		window.logout = function logout()
		{
			window.shopCar = null;
			window.order = store.clear();
			location.href = "/shitao/logout";
		};
		
	});
	</script>
</body>
</html>