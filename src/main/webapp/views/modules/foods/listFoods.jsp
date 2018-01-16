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
			<p  style="padding: 15px 15px 0px 15px;">
				<button id="bAddFoods" class="layui-btn">添加菜式</button>
			</p>
			<!-- 内容主体区域 -->
			<div style="padding: 15px;">
				<table class="layui-table">
					<colgroup>
						<col width="150">
						<col width="200">
						<col>
					</colgroup>
					<thead>
						<tr>
							<th>序号</th>
							<th>菜式名称</th>
							<th>价格</th>
							<th>数量</th>
							<th>折扣</th>
							<th>分类</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${foods}" var="food" varStatus="i">
							<tr>
								<td>${i.count}</td>
								<td>${food.food_name}</td>
								<td>${food.food_price}</td>
								<td>${food.food_num }</td>
								<td>${food.food_discount}</td>
								<td>${food.foodSort.sort_name}</td>
								<td>
									<div class="layui-btn-group">
										<button id="modifyFoods" foodsId="${food.id }"
											class="layui-btn layui-btn-primary layui-btn-small">
											<i class="layui-icon">&#xe642;</i>
										</button>
										<button id="deleterUser"
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
		});
	</script>
</body>
</html>
