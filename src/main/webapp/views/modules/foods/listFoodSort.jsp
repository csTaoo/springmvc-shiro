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
				<button id="bAddSort" class="layui-btn">添加分类</button>
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
							<th>分类名称</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${foodSorts}" var="foodSort" varStatus="i">
							<tr>
								<td>${i.count}</td>
								<td>${foodSort.sort_name}</td>
								<td>
									<div class="layui-btn-group">
										<button id="modifyFoods" foodSortId="${foodSort.id }"
											foodSortName="${foodSort.sort_name}"
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
		layui.use([ 'element', 'jquery', 'layer' ], function() {
			var element = layui.element;
			var layer = layui.layer;
			var $ = layui.jquery;
			$("button#modifyFoods").click(function() {
				
				var foodSortId = $(this).attr("foodSortId");
				var foodSortName = $(this).attr("foodSortName");
				var param = {};
				param.formType=2;
				param.value=foodSortName;
				param.title = "修改分类";
				layer.prompt(param, function(value, index, elem) {
					if(value != foodSortName)
					{
						var data = {id:foodSortId,sort_name:value,isUpdate:"true"};
						$.post("${APP_PATH}/foodsort/save",data,function(data, textStatus, jqXHR)
						{
							if(data=="保存失败")
							{
								layer.msg(data, {icon: 2});
							}
							else
							{
								layer.msg(data, {icon: 6});
							}
							
						},"html");
					}
					layer.close(index);
				});
			});
			
			
			$("#bAddSort").click(function(){
				var param = {};
				param.formType=2;
				param.title = "增加分类";
				layer.prompt(param, function(value, index, elem) {
					var data = {sort_name:value,isUpdate:"false"};
					$.post("${APP_PATH}/foodsort/save",data,function(data, textStatus, jqXHR)
					{
						if(data=="保存失败")
						{
							layer.msg(data, {icon: 2});
						}
						else
						{
							layer.msg(data, {icon: 6});
						}
						
					},"html");
					layer.close(index);
				});
			});
		});
	</script>
</body>
</html>
