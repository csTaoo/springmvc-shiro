<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../static/layui/css/layui.css" />
<script type="text/javascript" src="../static/layui/layui.js"></script>
<script type="text/javascript">
	layui.use([ 'form', 'element', 'layer' ], function() {
		var form = layui.form;
		var element = layui.element;
		var layer = layui.layer;

		var mes = '${shiroLoginFailure}';
		if (!mes == '' || !mes == undefined)
			layer.msg('用户名或密码错误', {
				icon : 2,
				time : 1000
			});

		//监听提交
		form.on('submit(formDemo)', function(data) {
			return true;
		});
	});
</script>
<title>登录</title>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-row">
			<div class="layui-md-12">
				<ul class="layui-nav layui-bg-green">
					<li class="layui-nav-item"><a href="">控制台</a></li>
					<li class="layui-nav-item"><a href="">商品管理</a></li>
					<li class="layui-nav-item"><a href="">用户</a></li>
					<li class="layui-nav-item"><a href="javascript:;">其它系统</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="">邮件管理</a>
							</dd>
							<dd>
								<a href="">消息管理</a>
							</dd>
							<dd>
								<a href="">授权管理</a>
							</dd>
						</dl></li>
				</ul>
			</div>
		</div>
		<div class="layui-row">
			<div class="layui-md-6 layui-col-md-offset4">
				<form class="layui-form"
					action="${pageContext.request.contextPath}/user/login"
					method="post">
					<div class="layui-form-item">
						<label class="layui-form-label">用户名：</label>
						<div class="layui-input-inline">
							<input type="text" name="username" required lay-verify="required"
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
							<button class="layui-btn" lay-submit lay-filter="formDemo">登录</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>