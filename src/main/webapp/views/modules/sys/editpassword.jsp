<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="APP_PATH" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>修改密码</title>
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
				<form class="layui-form layui-form-pane"
					action="${APP_PATH}/sys/user/updateUser" method="post">
					<input type="hidden" name="id" value="${user.id }" />
					<div class="layui-form-item">
						<label class="layui-form-label">用户名</label>
						<div class="layui-input-inline">
							<input type="text" name="name" required lay-verify="required"
								placeholder="请输入用户名" value="${user.name}" autocomplete="off"
								class="layui-input" disabled>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">真实姓名</label>
						<div class="layui-input-inline">
							<input type="text" name="realname" placeholder="请输入真实姓名"
								value="${user.realname}" autocomplete="off" class="layui-input">
						</div>
					</div>
				</form>
			</div>
		</div>

		<jsp:include page="../../include/footer.jsp"></jsp:include>
	</div>
	<script src="${APP_PATH}/static/layui/layui.js"></script>
</body>
</html>
