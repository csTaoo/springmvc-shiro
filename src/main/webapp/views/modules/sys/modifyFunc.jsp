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
<title>修改功能</title>
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
					action="${APP_PATH}/sys/user/updateFunc" method="post">
					<input type="hidden" name="id" value="${func.id }" />
					<div class="layui-form-item">
						<label class="layui-form-label">功能名称</label>
						<div class="layui-input-inline">
							<input type="text" name="name" required lay-verify="required"
								placeholder="请输入功能名" value="${func.name}" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">功能地址</label>
						<div class="layui-input-inline">
							<input type="text" name="realname" placeholder="请输入地址"
								value="${func.url}" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item" pane>
						<label class="layui-form-label">权限</label>
						<div class="layui-input-block">
							<input type="checkbox" name="like[write]" title="写作"> <input
								type="checkbox" name="like[read]" title="阅读" checked> <input
								type="checkbox" name="like[dai]" title="发呆">
						</div>
					</div>
					<div class="layui-form-item" pane>
						<label class="layui-form-label">状态</label>
						<div class="layui-input-block">
							<c:choose>
								<c:when test="${func.status==0 }">
									<input type="radio" name="status" value="0" title="启用" checked>
									<input type="radio" name="status" value="1" title="禁用">
								</c:when>
								<c:otherwise>
									<input type="radio" name="status" value="0" title="启用">
									<input type="radio" name="status" value="1" title="禁用" checked>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn" lay-submit lay-filter="userForm">立即提交</button>
							<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<jsp:include page="../../include/footer.jsp"></jsp:include>
	</div>
	<script src="${APP_PATH}/static/layui/layui.js"></script>
	<script>
		//JavaScript代码区域
		layui.use([ 'element', 'form', 'jquery' ], function() {
			var element = layui.element;
			var form = layui.form;
			var $ = layui.jquery;
			var layer = layui.layer;
			form.on('submit(userForm)', function(data) {
				return true;
			});
		});
	</script>
</body>
</html>
