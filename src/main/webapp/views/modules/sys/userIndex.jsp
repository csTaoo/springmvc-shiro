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
<title>用户管理</title>
<link rel="stylesheet" href="${APP_PATH}/static/layui/css/layui.css">
</head>
<body>
	<div class="layui-layout layui-layout-admin">

		<jsp:include page="../../include/header.jsp"></jsp:include>

		<jsp:include page="../../include/left-menu.jsp"></jsp:include>

		<!-- 这里是主要内容 -->
		<div class="layui-body">
			<div  style="padding: 15px 15px 0px 15px;">
				<button id="bAddUser" class="layui-btn">添加用户</button>
				<form class="layui-form" style="display:inline;"action="">
					<div class="layui-form-item" style="float:right;">
					    <div class="layui-input-inline">
					      <input type="text" name="username" placeholder="请输入搜索内容" autocomplete="off" class="layui-input"/>
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
							<th>用户名</th>
							<th>状态</th>
							<th>真实姓名</th>
							<th>余额</th>
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
								<td>${user.money }</td>
								<td>
									<div class="layui-btn-group">
										<button id="modifyUser"
											class="layui-btn layui-btn-primary layui-btn-small"
											url="${APP_PATH}/sys/user/modifyUser?id=${user.id}">
											<i class="layui-icon">&#xe642;</i>
										</button>
										<button id="delete" userid="${user.id}"
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
			//如果是从更改角色处过来提示更改信息
			var message = "${message}";
			
			if(message!='')
			{
				layer.msg(message,
						{
							icon : 1,
							time : 2000
						});
			}
			$("button#modifyUser").click(function() {

				top.location.href = $(this).attr("url");

			});
			
			$("#bAddUser").click(function(){
				
				window.location.href = "${APP_PATH}/sys/user/modifyUser";
			});
			
			$("button#delete").click(function(){
				var id = $(this).attr("userId");
				var url = "http://localhost:8080${APP_PATH}/sys/user/delete";
				doajax(url,id);
			});
			function doajax(url, id) {
				var load = layer.load();
				$.ajax({
					url : url,
					data : {
						userId : id
					},
					async : true,
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
			}
		});
	</script>
</body>
</html>
