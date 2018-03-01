<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="APP_PATH" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>餐厅后台管理系统</title>
<link rel="stylesheet" href="${APP_PATH}/static/layui/css/layui.css">
</head>
<body>
	<div class="layui-layout layui-layout-admin">

		<jsp:include page="../../include/header.jsp"></jsp:include>

		<jsp:include page="../../include/left-menu.jsp"></jsp:include>

		<!-- 这里是主要内容 -->
		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div style="padding: 15px;">
				<table id="table" lay-filter="table"></table>
			</div>
		</div>

		<jsp:include page="../../include/footer.jsp"></jsp:include>
	</div>
	<script src="${APP_PATH}/static/layui/layui.js"></script>
	<script type="text/html" id="toolbar">
      	<a class="layui-btn layui-btn-danger layui-btn-xs" style="height: 20px;line-height: 20px;" lay-event="del">删除</a>
	</script>
	<script type="text/html" id="foods">
		{{# layui.each(d.foods, function(index, item){ }}
			<span>{{ item.name }}</span>
			<span>{{ item.count }}</span>
		{{# }) }}
	</script>
	<script>
		//JavaScript代码区域
		layui.use(['element','jquery','layer','table'], function() {
			var element = layui.element;
			var $ = layui.jquery;
			var layer = layui.layer;
			var table = layui.table;
			
		  	table.render({
			    elem: '#table'
			    ,url: '/shitao/comment/listComment' //数据接口
			    ,cols: [[ //表头
			       {field: 'orderId', title: '订单编号',width:200, fixed: 'left'}
			      ,{field: 'username', title: '用户名', width:200}
			      ,{field: 'foods', title: '食物', width:200,templet:'#foods'}
			      ,{field:'content', title: '评价内容',width:180
			          ,templet:'<div>{{d.comment.content}}</div>'
			          }
			      ,{field: 'star', title: '星级',width:80
			    	  ,templet:'<div>{{d.comment.star}}</div>'}
			      ,{field: 'commentdate', title: '评价时间',width:168
			    	  ,templet:'<div>{{d.comment.date}}</div>'}
			      ,{field: 'operate', title: '操作',width:100
			    	  ,toolbar:'#toolbar'}
			    ]]
			  });
		   
		  	table.on('tool(table)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		  	  var data = obj.data; //获得当前行数据
		  	  var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		  	  var tr = obj.tr;
		  	  var orderId = data.orderId;
		  	 
		  	   if(layEvent === 'del'){ //删除
		  	    layer.confirm('你确认要删除？', function(index)
		  	    {
		  	      //向服务端发送删除指令
			  	  $.ajax({
						url : "http://localhost:8080${APP_PATH}/comment/delComment",
						data : {
							orderId : orderId
						},
						async : true,
						success : function(data) {
							layer.msg('请求成功', {
								icon : 6,
								time : 1000
							},function(){
								obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
						  	    layer.close(index);
							});
						},
						error : function() {
						},statusCode: {
						    401: function() {
						    	layer.msg('您没有权限', {
									icon : 2,
									time : 1000
								});
						      },
						    403:function()
						    {
						    	layer.msg('此功能已被管理员停用', {
									icon : 2,
									time : 1000
								});
						    }
						 }
					});
		  	      
		  	    	});
		  	  	}
		  	});
		  	
		});
	</script>
</body>
</html>
