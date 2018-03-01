<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" />
<style type="text/css">
.header-color
{
	background-color:#19A094;
}
.layui-fluid
{
	margin-top:10em;
}
.header-logo
{
	padding:0.2em;
	font-size: 1.5em;
	color:#C4C5C9;
}
</style>
<title>用户注册</title>
</head>
<body>
	<div class="layui-header header-color">
		<div class="header-logo">用户注册</div>
	</div>
	<div class="layui-fluid">
		<div class="layui-row">
			<div class="layui-md-6 layui-col-md-offset4">
				<form class="layui-form"
					action="${pageContext.request.contextPath}/sys/user/register"
					method="post">
					<div class="layui-form-item">
						<label class="layui-form-label">用户名：</label>
						<div class="layui-input-inline">
							<input type="text" name="name" required lay-verify="required"
								placeholder="请输入用户名" autocomplete="off" class="layui-input" />
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">密码：</label>
						<div class="layui-input-inline">
							<input type="password" name="password" required
								lay-verify="required" placeholder="请输入密码" autocomplete="off"
								class="layui-input" />
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn" lay-submit lay-filter="formDemo">注册</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>