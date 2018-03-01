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
			<div  style="padding: 15px 15px 0px 15px;">
				<button id="bAddFoods" class="layui-btn">添加菜式</button>
				<form class="layui-form" style="display:inline;"action="">
					<div class="layui-form-item" style="float:right;">
					    <div class="layui-input-inline">
					      <input type="text" name="foodname" placeholder="请输入搜索内容" autocomplete="off" class="layui-input"/>
					    </div>
					    <button id="search" class="layui-btn">搜索</button>
					</div>
				</form>
			</div>
			<!-- 内容主体区域 -->
			<div style="padding: 15px;">
				<table class="layui-table">
					<colgroup>
						<col width="80">
						<col width="80">
					</colgroup>
					<thead>
						<tr>
							<th>序号</th>
							<th>图片</th>
							<th>菜式名称</th>
							<th>价格</th>
							<th>折扣</th>
							<th>分类</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${foods}" var="food" varStatus="i">
							<tr>
								<td>${i.count}</td>
								<td>
									<img style="width:80px;height:80px;" src="${APP_PATH}/static/upload/foods/${food.food_img}" alt="img">
								</td>
								<td>${food.food_name}</td>
								<td>${food.food_price}</td>
								<td>${food.food_discount}</td>
								<td>${food.foodSort.sort_name}</td>
								<td>
									<div class="layui-btn-group">
										<button id="modifyFoods" foodsId="${food.id }"
											class="layui-btn layui-btn-primary layui-btn-small">
											<i class="layui-icon">&#xe642;</i>
										</button>
										<button id="delete" foodsId="${food.id }"
											class="layui-btn layui-btn-primary layui-btn-small">
											<i class="layui-icon">&#xe640;</i>
										</button>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<jsp:include page="../../include/footer.jsp"></jsp:include>
	</div>
	<script src="${APP_PATH}/static/layui/layui.js"></script>
	<script>
		//JavaScript代码区域
		layui.use(['element','jquery','layer'], function() {
			var element = layui.element;
			var $ = layui.jquery;
			var layer = layui.layer;
			var mes = '${mes}';
			
			if(mes == '保存成功')
			{
				layer.msg('保存成功', {area:'100px',icon: 6});
			}
			
			if (mes == '保存失败')
			{
				layer.msg('保存失败', {area:'100px',icon: 2});
			}
			
			$("button#modifyFoods").click(function(){
				
				var foodsId = $(this).attr("foodsId");
				window.location.href = "${APP_PATH}/foods/edit?foodsId="+foodsId;
				
			});
			
			$("#bAddFoods").click(function(){
				
				window.location.href = "${APP_PATH}/foods/edit";
			});
			
			$("button#delete").click(function() {
				var url = "http://localhost:8080${APP_PATH}/foods/delete";
				var id = $(this).attr("foodsId");
				doajax(url,id);
			});

			function doajax(url, id) {
				var load = layer.load();
				$.ajax({
					url : url,
					data : {
						foodsId : id
					},
					async : true,
					dataType:'json',
					success : function(data) {
						layer.close(load);
						layer.msg('请求成功', {
							icon : 6,
							time : 1000
						},function(){
							location.reload();
						});
					},
					error : function() {
						layer.close(load);
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
			}
		});
	</script>
</body>
</html>
