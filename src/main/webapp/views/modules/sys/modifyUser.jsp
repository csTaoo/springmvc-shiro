<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>修改用户</title>
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
				<form class="layui-form layui-form-pane" action="">
					<div class="layui-form-item">
						<label class="layui-form-label">用户名</label>
						<div class="layui-input-inline">
							<input type="text" name="title" required lay-verify="required"
								placeholder="请输入用户名" value="${user.name}" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">角色</label>
						<div class="layui-input-block">
							<select name="city" lay-filter="role">
								<c:forEach items="${roles}" var="role">
									<c:choose>
										<c:when test="${role.name eq user.role.name}">
											<option value="${role.id}" selected><c:out
													value="${role.name}"></c:out></option>
										</c:when>
										<c:otherwise>
											<option value="${role.id}"><c:out
													value="${role.name}"></c:out></option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="layui-form-item" pane>
						<label class="layui-form-label">权限</label>
						<div class="layui-input-block">
							<c:forEach items="${user.role.permissions}" var="permission">
								<div
									class="layui-form-checkbox layui-bg-green layui-checkbox-disbaled layui-disabled">
									<span style="background-color: #009688 !important;">${permission.name}</span><i
										class="layui-icon">&#xe618</i>
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="layui-form-item" pane>
						<label class="layui-form-label">停用</label>
						<div class="layui-input-block">
							<input type="checkbox" name="switch" lay-skin="switch"
								lay-text="启用|停用">
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
							<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						</div>
					</div>
				</form>
			</div>
		</div>

		<jsp:include page="../../include/footer.jsp"></jsp:include>
	</div>
	<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
	<script>
		//JavaScript代码区域
		layui.use([ 'element', 'form' ,'jquery'], function() {
			var element = layui.element;
			var form = layui.form;
			var $ = layui.jquery;
			form.on('select(role)', function(data) {
				var value = data.value;
				$.ajax()
			});
		});
	</script>
</body>
</html>
