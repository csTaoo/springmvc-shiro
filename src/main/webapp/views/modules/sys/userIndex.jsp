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
<title>springmvc-shiro</title>
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
				<table class="layui-table">
					<colgroup>
						<col width="150">
						<col width="200">
						<col>
					</colgroup>
					<thead>
						<tr>
							<th>序号</th>
							<th>用户名</th>
							<th>状态</th>
							<th>所属群组</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="user" varStatus="i">
							<tr>
								<td>${i.count}</td>
								<td>${user.name}</td>
								<td>
									<c:choose>
										<c:when test="${user.status==0 }">
											启用
										</c:when>
										<c:otherwise>
											停用
										</c:otherwise>
									</c:choose>
								</td>
								<td><c:if test="${empty user.realname}">
										暂无
									</c:if> <c:if test="${not empty user.realname}">
										${user.realname}
									</c:if></td>
								<td>
									<div class="layui-btn-group">
										<button id="modifyUser"
											class="layui-btn layui-btn-primary layui-btn-small"
											url="${APP_PATH}/sys/user/modifyUser?id=${user.id}">
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
		layui.use([ 'form', 'element', 'layer' ], function() {
			var element = layui.element;
			var layer = layui.layer;
			var $ = layui.jquery;
			$("button#modifyUser").click(function() {

				top.location.href = $(this).attr("url");

			});
		});
	</script>
</body>
</html>
