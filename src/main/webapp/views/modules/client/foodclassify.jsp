<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<style type="text/css">
	*{
		margin:0px;
		padding:0px;
	}
	h3
	{
		margin:0px;
		padding:0px;
		font-size: 1em;
	}
	p
	{
		margin:0px;
		padding:0px;
		font-size: 0.8em;
	}
	ul
	{
		list-style:none;
		padding:0px;
	}
	
	.content
	{
		position:relative;
		overflow:hidden;
	}
	.content .left-navbar 
	{
		position:relative;
		float:left;
		width:30%;
		height:100%;
		overflow-x:hidden;
		overflow-y:auto;	
	}
	.content .left-navbar .left  li
	{
		padding:5px;
		text-align:center;
		border-bottom:1px solid #F5F5F5;
	}
	.content .right-navbar 
	{
		position:relative;
		float:left;
		width:68%;
		height:100%;
		overflow-x:hidden;
		overflow-y:auto;
	}
	.content .right-navbar .right li
	{
		padding:5px;
		height:100px;
		border-bottom:1px solid #F5F5F5;
	}
	li img
	{
		width:80px;
		height:80px;
		float:left;
	}
	.item
	{
		margin-left:90px;
		height:100%;
	}
	.item .item-left
	{
		position:relative;
		float:left;
	}
	.item .item-right
	{
		position:relative;
		float:right;
		color:blue;
		align:center;
	}
</style>
<!--[if (gte IE 9)|!(IE)]><!-->
<script src="${APP_PATH}/static/jquery/jquery-1.8.3.min.js"></script>
<script src="${APP_PATH}/static/client/order/order.js"></script>
</head>
<body>
	<header data-am-widget="header" class="am-header am-header-default">
		<h1 class="am-header-title">
			<a href="#title-link" class=""> 点餐系统 </a>
		</h1>
	</header>
	<div class="content">
		<div class="left-navbar">
			<div class="left">
				<ul>
					<c:forEach items="${sorts}" var="sort">
						<li id="${sort.id}">${sort.sort_name}</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="right-navbar">
			<div class="right">
				<ul>
					<li>
						<span>请选择左边分类</span>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<order:order content="订单" type="订单"/>
	<!-- 页脚 -->
	<div data-am-widget="navbar" class="am-navbar am-cf am-navbar-default "
		id="">
		<ul class="am-navbar-nav am-cf am-avg-sm-4">
			<li><a href="${APP_PATH}/a/index/indexShow" > <span class="am-icon-phone"></span>
					<span class="am-navbar-label">点餐</span>
			</a></li>
			<li><a href="${APP_PATH}/a/index/foodclassify" > <span class="am-icon-bars"></span>
					<span class="am-navbar-label">分类</span>
			</a></li>
			<li id="dropDown"><a href="javascript:void(0);" > <span
					class="am-icon-cart-plus"></span> <span class="am-navbar-label">订单</span>
			</a></li>
			<li><a href="${APP_PATH}/a/index/mine" > <span class="am-icon-user"></span>
					<span class="am-navbar-label">我的</span>
			</a></li>
		</ul>
	</div>

	<!--<![endif]-->
	<!--[if lte IE 8 ]>
    <script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
    <script src="assets/js/amazeui.ie8polyfill.min.js"></script>
    <![endif]-->
	<script src="${APP_PATH}/static/amazeui/js/amazeui.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#dropDown").click(function() {
				$modal = $("#my-actions");
				$modal.modal();
			});
			
			$(".left li").click(function(){
				var value = $(this).attr("id");
				$(".right ul").empty();
				$.ajax({
					url : "${APP_PATH}/foods/listFoodsBySort",
					data : {
						sortId : value
					},
					async : false,
					dataType : "json",
					success : function(data){
						createThrouthJson(data.data);
					},
					error:function(){
						alert("系统出错");
					}
					
				});
			});
			
			function createThrouthJson(data)
			{
				var dataItem = '<li><img src=""/><div class="item"><div class="item-left"><h3></h3><p></p><p></p><p></p></div><div class="item-right"><i id="addToCar" class="am-icon-plus"></i></div></div></li>';
				
				for(var i=0;i<data.length;i++)
				{
					var $JdataItem = $(dataItem);
					$JdataItem.children("img").attr("src","${APP_PATH}/static/upload/"+data[i].food_img);
					$JdataItem.find("h3").html(data[i].food_name);
					$JdataItem.find("p").eq(0).html("折扣："+data[i].food_discount);
					$JdataItem.find("p").eq(1).html("月售："+data[i].oo);
					$JdataItem.find("p").eq(2).html("剩余："+data[i].food_num);
					$JdataItem.find("i").attr("fid",data[i].id);
					$JdataItem.find("i").attr("money",data[i].food_price);
					$JdataItem.find("i").attr("name",data[i].food_name);
					$(".right ul").append($JdataItem);
				}
				initAddBtnListener();
			}
			
			
			function initAddBtnListener()
			{
				/*
				*添加至订单列表
				*/
				$("i#addToCar").click(function(){
					var id = $(this).attr("fid");
					var money = $(this).attr("money");
					var name = $(this).attr("name");
					var $countP = $(this).parent().prev().find("p").eq(2);
					var a = $countP.text().split("：");
					var count = parseInt(a[1]);
					if(parseInt(count) == 0)
					{
						alert("数量不足");
						return;
					}
					else
					{
						count -= 1;
						$countP.text("剩余："+count);
					}
					var tempItem = 
					{
						id:id,
						money:parseInt(money),
						name:name,
						count:1
					};
					
					window.shopCar.addItem(tempItem);
					createLis(window.shopCar);
					console.log(JSON.stringify(window.shopCar));
				});
			}
			
		});
	</script>
</body>
</html>