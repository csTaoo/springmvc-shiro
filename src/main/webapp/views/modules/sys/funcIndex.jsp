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
<title>功能管理</title>
<link rel="stylesheet"
	href="${APP_PATH}/static/layui/css/layui.css">
</head>
<body>
	<div class="layui-layout layui-layout-admin">

		<jsp:include page="../../include/header.jsp"></jsp:include>

		<jsp:include page="../../include/left-menu.jsp"></jsp:include>

		<!-- 这里是主要内容 -->
		<div class="layui-body">
			<div  style="padding: 15px 15px 0px 15px;">
				<form class="layui-form" style="display:inline;"action="">
					<div class="layui-form-item" style="float:right;">
					    <div class="layui-input-inline">
					      <input type="text" name="funcname" placeholder="请输入搜索内容" autocomplete="off" class="layui-input"/>
					    </div>
					    <button id="search" class="layui-btn">搜索</button>
					</div>
				</form>
			</div>
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
							<th>功能名称</th>
							<th>功能地址</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${funcs}" var="func" varStatus="i">
							<tr>
								<td>${i.count}</td>
								<td>${func.name}</td>
								<td>${func.url }</td>
								<td>
									<c:choose>
										<c:when test="${func.status == 0 }">
											启用
										</c:when>
										<c:otherwise>
											禁用
										</c:otherwise>
									</c:choose>
								</td>
								<td>
									<div class="layui-btn-group">
										<button id="modifyFunc" fid="${func.id}"
											class="layui-btn layui-btn-primary layui-btn-small">
											<i class="layui-icon">&#xe642;</i>
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
			
			$("button#modifyFunc").click(function(){
				var load = layer.load();
				var id = $(this).attr("fid");
				$.ajax({
					url:'${APP_PATH}/sys/func/update',
					type:'get',
					data:
					{
						id : id
					},
					async:true,
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
			});
		});
	</script>
</body>
</html>
