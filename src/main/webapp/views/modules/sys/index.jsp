<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="APP_PATH" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>后台管理</title>
<link rel="stylesheet" href="${APP_PATH}/static/layui/css/layui.css">
<script type="text/javascript" src="${APP_PATH}/static/websocket/stomp.min.js"></script>
<script type="text/javascript" src="${APP_PATH}/static/utils/dateformat.js"></script>
<style type="text/css">
.layui-icon{
	font-size: 1.3em;
}
i:hover{
	cursor: pointer;
}
.layui-elem-quote{
    font-size: 1.5em;
}
</style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
	
  <jsp:include page="../../include/header.jsp"></jsp:include>
  
  <jsp:include page="../../include/left-menu.jsp"></jsp:include>
  
  <audio id="media" src="${APP_PATH}/static/audio/mes.mp3" style="display:none;"></audio>
  <!-- 这里是主要内容 -->
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">
	    <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
		  <ul class="layui-tab-title">
		    <li class="layui-this">待处理</li>
		    <li>已处理</li>
		  </ul>
		  <div class="layui-tab-content">
		  	<div id="nonSolved" class="layui-tab-item layui-show">
		  		<ul id="orderUl">
	    			<li><blockquote id="nonOrdertip" class="layui-elem-quote">暂无新订单</blockquote></li>
	    		</ul>
		  	</div>
	    	<div id="Solved" class="layui-tab-item">
	    		<ul>
	    		</ul>
	    	</div>
		  </div>
		</div>
    </div>
  </div>
  <jsp:include page="../../include/footer.jsp"></jsp:include>
</div>
<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script>
layui.use(['element','jquery','layer'], function(){
  window.orderArr = {};
  var element = layui.element;
  var layer = layui.layer;
  var $ = layui.jquery;
  initFinishOrderEvent();
  var socket = new WebSocket("ws://localhost:8080/shitao/payMessageHandler");
	socket.onopen=function()
	{
		console.log("已连接");
	};
	socket.onmessage= function(event)
	{
		console.log(event.data);
		$("#nonOrdertip").hide();
		var liStr = '<li><blockquote class="layui-elem-quote"><i id="finish" class="layui-icon" style="float:right;">&#xe605;</i><i id="refuse" class="layui-icon" style="float:right;">&#x1006;</i></blockquote></li>';
		var data = JSON.parse(event.data);
		orderArr[data.id] = data;
		var foods = data.foods;
		var text = '';
		for(var i=0;i<foods.length;i++)
		{
			 if(i==foods.length-1)
			 {
				 text += foods[i].name+"---数量:"+foods[i].count;
			 }
			 else
			{
				 text += foods[i].name+"---数量:"+foods[i].count+"<br/>"; 
			}
		}
		var $liDom = $(liStr);
		$liDom.find("i.layui-icon").attr("oid",data.id);
		$liDom.children("blockquote").prepend(text);
		$liDom.children("blockquote").prepend("桌号："+data.table+"<br/>");
		$("#orderUl").prepend($liDom);
		var media = $("#media")[0];
		media.play();
		initFinishOrderEvent();
	};
	function sendMessage()
	{
		socket.send("mes");
	}
	
	function initFinishOrderEvent()
	{
		$("i#finish").click(function(){
			var my = $(this);
			var id = $(this).attr("oid");
			var param = {
				order:JSON.stringify(orderArr[id])
			};
			$.post("${APP_PATH}/a/index/finishOrder",param,function(data, textStatus, jqXHR)
			{
				if(data=="success")
				{
					my.css("color","green");
					my.parent().css("background-color","#9D9DA0");
					$("#Solved").children("ul").prepend(my.parent().parent());
					var oCount = $("#nonSolved").find("li").not("#nonOrdertip");
					if(oCount == 0)
					{
						$("#nonOrdertip").show();
					}
					delete orderArr[id];
				}
				else
				{
					layer.msg("处理失败", {icon: 2,time:1000});
				}
						
			},"html").fail(function(jqXHR){
						if(401 == jqXHR.status)
						{
							layer.msg("权限不足", {icon: 2});
						}
						else if(403 == jqXHR.status)
						{
							layer.msg("此功能已被停用", {icon: 2});
						}
					});
		});
		
		$("i#refuse").click(function(){
			$(this).parent().parent().remove();
		});
	}
});
</script>
</body>
</html>
