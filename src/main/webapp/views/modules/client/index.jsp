<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
</head>
<body>
	<header data-am-widget="header" class="am-header am-header-default">
		<h1 class="am-header-title">
			<a href="#title-link" class=""> 点餐系统 </a>
		</h1>
	</header>
	<div data-am-widget="slider" class="am-slider am-slider-a5"
		data-am-slider='{&quot;directionNav&quot;:false}'>
		<ul class="am-slides">
			<c:forEach var="carousel" items="${list }">
				<li>
					<img src="${APP_PATH}/static/upload/${carousel.imgPath}">
				</li>
			</c:forEach>
		</ul>
	</div>
	<!-- 内容区域 -->
	<div data-am-widget="list_news"
		class="am-list-news am-list-news-default">
		<!--列表标题-->
		<div class="am-list-news-hd am-cf">
			<!--带更多链接-->
			<a href="###" class="">
				<h2>美食推荐</h2> <span class="am-list-news-more am-fr">更多
					&raquo;</span>
			</a>
		</div>
		<div class="am-list-news-bd">
			<ul class="am-list">
				<!--缩略图在标题左边 一个list为一个item-->
				<li
					class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-left">
					<!-- 左图 -->
					<div class="am-u-sm-4 am-list-thumb">
						<a href="http://www.douban.com/online/11624755/" class=""> <img
							src="http://img3.douban.com/lpic/o637240.jpg" alt="我最喜欢的一张画" />
						</a>
					</div>

					<div class=" am-u-sm-8 am-list-main">
						<h3 class="am-list-item-hd">
							<a href="http://www.douban.com/online/11624755/" class="">我最喜欢的一张画</a>
						</h3>

						<div class="am-list-item-text">你最喜欢的艺术作品，告诉大家它们的------名图画，色彩，交织，撞色，线条雕塑装置当代古代现代作品的照片美我最喜欢的画群296795413进群发画，少说多发图，</div>

					</div>
				</li>

				<li
					class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-left">
					<div class="am-u-sm-4 am-list-thumb">
						<a href="http://www.douban.com/online/11624755/" class=""> <img
							src="http://img3.douban.com/lpic/o637240.jpg" alt="我最喜欢的一张画" />
						</a>
					</div>

					<div class=" am-u-sm-8 am-list-main">
						<h3 class="am-list-item-hd">
							<a href="http://www.douban.com/online/11624755/" class="">我最喜欢的一张画</a>
						</h3>

						<div class="am-list-item-text">你最喜欢的艺术作品，告诉大家它们的------名图画，色彩，交织，撞色，线条雕塑装置当代古代现代作品的照片美我最喜欢的画群296795413进群发画，少说多发图，</div>

					</div>
				</li>
			</ul>
		</div>
	</div>
	<!-- 隐藏的上拉框 -->
	<div class="am-modal-actions" id="my-actions">
		<div class="am-modal-actions-group">
			<ul class="am-list">
				<li class="am-modal-actions-header">订餐列表列表</li>
				<li>
				  <a href="javascript:void(0);">
				     <span style="float: left">鸡翅</span>
				     <span style="float: right">
				     <i class="am-icon-minus"></i>
				     <i style="font-style:normal;margin-left:5px;margin-right:5px;">1</i>
				     <i class="am-icon-plus"></i>
				     </span>
				  </a>
				</li>
				<li>
				  <a href="javascript:void(0);">
				     <span style="float: left">鸡翅</span>
				     <span style="float: right">
				     <i class="am-icon-minus"></i>
				     <i style="font-style:normal;margin-left:5px;margin-right:5px;">1</i>
				     <i class="am-icon-plus"></i>
				     </span>
				  </a>
				</li>
			</ul>
		</div>
		<div class="am-modal-actions-group">
			<button class="am-btn am-btn-secondary am-btn-block">下单
			</button>
			<button class="am-btn am-btn-secondary am-btn-block"
				data-am-modal-close>取消</button>
		</div>
	</div>
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
			<li id="dropDown"><a href="javascript:void(0);" class=""> <span
					class="am-icon-cart-plus"></span> <span class="am-navbar-label">订单</span>
			</a></li>
			<li><a href="${APP_PATH}/a/index/mine" class=""> <span class="am-icon-user"></span>
					<span class="am-navbar-label">我的</span>
			</a></li>
		</ul>
	</div>
	<!--在这里编写你的代码-->

	<!--[if (gte IE 9)|!(IE)]><!-->
	<script src="${APP_PATH}/static/jquery/jquery-1.8.3.min.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8 ]>
    <script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
    <script src="assets/js/amazeui.ie8polyfill.min.js"></script>
    <![endif]-->
	<script src="${APP_PATH}/static/amazeui/js/amazeui.min.js"></script>
	<script type="text/javascript">
		$("#dropDown").click(function() {
			$modal = $("#my-actions");
			$modal.modal();
		});
	</script>
</body>
</html>