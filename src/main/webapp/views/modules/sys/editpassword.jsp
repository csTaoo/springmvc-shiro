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
					action="" method="post">
					<div class="layui-form-item">
						<label class="layui-form-label">输入原密码</label>
						<div class="layui-input-inline">
							<input id="oldpwd" type="password" name="name" required lay-verify="required"
								placeholder="输入原密码" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">新密码</label>
						<div class="layui-input-inline">
							<input  id="newpwd" type="password" name="realname" placeholder="请输入新密码"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">确认新密码</label>
						<div class="layui-input-inline">
							<input id="confirmpwd" type="password" name="realname" placeholder="请确认新密码"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button type="button" id="save" class="layui-btn" lay-submit lay-filter="userForm">保存</button>
						</div>
					</div>
				</form>
			</div>
		</div>

		<jsp:include page="../../include/footer.jsp"></jsp:include>
	</div>
	<script src="${APP_PATH}/static/layui/layui.js"></script>
	<script type="text/javascript">
	layui.use(['element','jquery','layer'], function() {
		var element = layui.element;
		var $ = layui.jquery;
		var layer = layui.layer;
		$("#confirmpwd").blur(function(){
			
			var confirm = $("#newpwd").val() != $(this).val();
			if(confirm)
			{
				layer.msg('密码不一致', {
					icon : 2,
					time : 1000
				});
				return;
			}
		});
		
		$("#save").click(function(){
			
			$.ajax({
				url:"${APP_PATH}/sys/user/updatepassword",
				data:
				{
					oldpwd: $("#oldpwd").val(),
					newpwd: $("#newpwd").val(),
					confirmpwd: $("#confirmpwd").val()
				},
				async:true,
				success:function(data)
				{
					if(data == "success")
					{
						layer.msg("修改成功",{
							icon : 6,
							time : 1000
						});
					}else
					{
						layer.msg(data,{
							icon : 2,
							time : 1000
						});
					}
				},
				error:function()
				{
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
