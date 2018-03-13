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
<script src="${APP_PATH}/static/jquery/jquery-1.8.3.min.js"></script>
<script src="${APP_PATH}/static/raty/jquery.raty.js"></script>
<script src="${APP_PATH}/static/utils/dateformat.js"></script>
<script src="${APP_PATH}/static/amazeui/js/amazeui.min.js"></script>
<script src="${APP_PATH}/static/client/order/order.js"></script>
</head>
<body>
	<!-- 页头 -->
	<header data-am-widget="header" class="am-header am-header-default">
		<h1 class="am-header-title">
			<a href="#title-link" class=""> 点餐系统 </a>
		</h1>
	</header>
	<!-- 内容区域 -->
	<div data-am-widget="list_news"
		class="am-list-news am-list-news-default">
		<h1>订单</h1>
		<div class="am-list-news-bd">
			<ul id="list"  class="am-list">
				<!-- 内容由js填充 -->
			</ul>
		</div>
	</div>
	<!-- 评价弹出 -->
	<div class="am-popup" id="my-popup">
	  <div class="am-popup-inner">
	    <div class="am-popup-hd">
	      <h4 class="am-popup-title">评价</h4>
	      <span data-am-modal-close
	            class="am-close">&times;</span>
	    </div>
	    <div class="am-popup-bd">
	      <form class="am-form">
			  <fieldset>
			    <legend>评价</legend>
			    <div class="am-form-group">
			      <label for="doc-ta-1">评价内容</label>
			      <textarea class="" rows="5" id="doc-ta-1"></textarea>
			    </div>
			    <p><div id="star"></div></p>
			    <p>分值：<span id="result"></span></p>
			    <p><button id="commit" type="button" class="am-btn am-btn-default">提交</button></p>
			  </fieldset>
		  </form>
	    </div>
	  </div>
	</div>
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
			<li><a href="${APP_PATH}/a/index/mine" class=""> <span class="am-icon-user"></span>
					<span class="am-navbar-label">我的</span>
			</a></li>
		</ul>
	</div>
	<script type="text/javascript">
		var orderId = '';
		var liStr = '<li class="am-g am-list-item-desced"><div ></div></li>';
		var btnStr = '<button id="comment" type="button" class="am-btn am-btn-primary am-round am-btn-xs">前去评价</button>';
		var spanStr = '<span>菜名</span><span style="margin-left:5px;">x1</span><br/>';
		var hrStr = '<h3 style="margin:0;">评价：</h3><hr data-am-widget="divider" style="margin-top:0;" class="am-divider am-divider-dotted" />';
		var starStr = '<span style="color:#FFD94B;" class="am-icon-star am-icon-sm"></span>';
		$.ajax
		({
			url:"${APP_PATH}/a/index/getUserOrders",
			type:'post',
			dataType:'json',
			async:true,
			success:function(data)
			{
				var $list = $("#list");
				for(var i=0;i<data.length;i++)
				{
					var domLi = $(liStr);
					var foods = data[i].foods;
					for(var j=0;j<foods.length;j++)
					{
						var domSpan = $(spanStr);
						domSpan.eq(0).html(foods[j].name);
						domSpan.eq(1).html(foods[j].count);
						domLi.append(domSpan);
					}
					if(data[i].comment == null)
					{
						var domBtn = $(btnStr);
						domBtn.attr("orderid",data[i].orderId);
						domLi.append(domBtn);
					}
					else
					{
						var domHr = $(hrStr);
						domLi.append(domHr);
						domLi.append(data[i].comment.content+"<br/>");
						var star = data[i].comment.star;
						for(var j=0;j<star ;j++)
						{
							domLi.append($(starStr));
						}
					}
					$list.append(domLi);
				}
				
				$("button#comment").click(function() {
					$commentModal = $("#my-popup");
					$commentModal.modal();
					orderId = $(this).attr("orderid");
				});
			},
			error:function()
			{
				
			},statusCode: {
			    401: function() {
			    	$("#tipsmes").text("没有权限");
					$tipsmodal.modal();
			      },
			    403:function()
			    {
			    	$("#tipsmes").text("此功能已被管理员禁用");
					$tipsmodal.modal();
			    }
			 }
		});
		
		/*
		* 评论按钮单击
		*/
		$("#commit").click(function(){
			
			var content = $("#doc-ta-1").val();
			var star = $("#result").text();
			var data = 
			{
					content : content,
					orderId : orderId,
					star :star
			};
			comment(data);
		});
		
		/*
		* 订单列表单击
		*/
		$("#dropDown").click(function() {
			$modal = $("#my-actions");
			$modal.modal();
		});
		
		/*
		* 星级评分插件
		*/
		$("#star").raty({
			hints: ['1','2', '3', '4', '5'],
			path: "/shitao/static/raty/css/img",
			starOff: 'star-off-big.png',
			starOn: 'star-on-big.png',
			size: 24,
			start: 40,
			showHalf: true,
			number:5,
			target: $("#result"),
			targetKeep : true,//targetKeep 属性设置为true，用户的选择值才会被保持在目标DIV中，否则只是鼠标悬停时有值，而鼠标离开后这个值就会消失
			click: function (score, evt) {
			}
		});
		
		/*
		*评论请求
		*/
		function comment(data)
		{
			$.ajax
			({
				url:"${APP_PATH}/a/index/comment",
				type:'post',
				async:true,
				data:data,
				success:function(data)
				{
					$("#tipsmes").text("评论成功");
					$tipsmodal.modal();
					$commentModal.modal('close');
				},
				error:function()
				{
					alert("网络错误");
				},statusCode: {
				    401: function() {
				    	$("#tipsmes").text("没有权限");
						$tipsmodal.modal();
				      },
				    403:function()
				    {
				    	$("#tipsmes").text("此功能已被管理员禁用");
						$tipsmodal.modal();
				    }
				 }
			});
		}
	</script>
</body>
</html>