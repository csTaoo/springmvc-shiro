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
	href="${pageContext.request.contextPath}/static/layui/css/layui.css">
</head>
<body>
	<div class="layui-layout layui-layout-admin">

		<jsp:include page="../../include/header.jsp"></jsp:include>

		<jsp:include page="../../include/left-menu.jsp"></jsp:include>

		<!-- 这里是主要内容 -->
		<div class="layui-body">
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
							<th>角色名称</th>
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
										<button id="modifyFunc" url="${APP_PATH}/sys/func/modifyFunc?id=${func.id}"
											class="layui-btn layui-btn-primary layui-btn-small">
											<i class="layui-icon">&#xe642;</i>
										</button>
										<button id="deleterFunc"
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
	<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
	<script>
		//JavaScript代码区域
		layui.use(['element','jquery'], function() {
			var element = layui.element;
			var $ = layui.jquery;
			
			$("button#modifyFunc").click(function(){
				top.location.href = $(this).attr("url");
			});
			
			
		});
	</script>
</body>
</html>
