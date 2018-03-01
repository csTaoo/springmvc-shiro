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
<script type="text/javascript" src="${APP_PATH}/static/utils/dateformat.js"></script>
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
	<script type="text/html" id="foods">
		{{# layui.each(d.foods, function(index, item){ }}
			<span>{{ item.name }}</span>
			<span>{{ item.count }}</span>
		{{# }) }}
	</script>
	<script type="text/html" id="orderdate">
		{{ new Date(d.date).Format("yyyy/MM/dd HH:mm:ss") }}
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
			    ,url: '/shitao/order/listOrder' //数据接口
			    ,cols: [[ //表头
			       {field: 'orderId', title: '订单编号',width:200, fixed: 'left'}
			      ,{field: 'username', title: '用户名', width:200}
			      ,{field: 'foods', title: '食物', width:200,templet:'#foods'}
			      ,{field: 'money', title: '金额', width:200}
			      ,{field: 'date', title: '订单时间',width:308
			    	  ,templet:'#orderdate'}
			    ]]
			  });
		   
		  	table.on('tool(table)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		  	  var data = obj.data; //获得当前行数据
		  	  var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		  	  var tr = obj.tr; 
		  	 
		  	   if(layEvent === 'del'){ //删除
		  	    layer.confirm('你确认要删除？', function(index)
		  	    {
		  	      obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
		  	      layer.close(index);
		  	      //向服务端发送删除指令
		  	    	});
		  	  	}
		  	});
		});
	</script>
</body>
</html>
