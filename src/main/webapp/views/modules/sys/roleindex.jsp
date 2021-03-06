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
<title>角色管理</title>
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
				<button id="bAddFoods" class="layui-btn">添加角色</button>
				<form class="layui-form" style="display:inline;"action="">
					<div class="layui-form-item" style="float:right;">
					    <div class="layui-input-inline">
					      <input type="text" name="rolename" placeholder="请输入搜索内容" autocomplete="off" class="layui-input"/>
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
							<th>角色名称</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${roles}" var="role" varStatus="i">
							<c:if test="${role.name ne '系统管理' }">
							<tr>
								<td>${i.count}</td>
								<td>${role.name}</td>
								<td>
									<div class="layui-btn-group">
										<button id="modifyRole" roleid="${role.id }" rolename="${role.name }"
											class="layui-btn layui-btn-primary layui-btn-small">
											<i class="layui-icon">&#xe642;</i>
										</button>
										<button id="delete" roleid="${role.id }" 
											class="layui-btn layui-btn-primary layui-btn-small">
											<i class="layui-icon">&#xe640;</i>
										</button>
									</div>
								</td>
							</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>

		<jsp:include page="../../include/footer.jsp"></jsp:include>
	</div>
	<script src="${APP_PATH}/static/layui/layui.js"></script>
	<script>
		layui.use([ 'element', 'layer' ,'form','jquery'], function() {
			var element = layui.element;
			var layer = layui.layer;
			var $ = layui.jquery;
			var form = layui.form;
			
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
				
			//拼装更改角色表单	
			var start ='<form style="margin-top:10px;" class="layui-form" action="${APP_PATH}/sys/role/updateRolePermission" method="post">';
			var item ='<div class="layui-form-item">';
			var label ='<label class="layui-form-label">角色名</label>';
			var plabel='<label class="layui-form-label">权限</label>';
			var block ='<div class="layui-input-block">';
			var inline ='<div class="layui-input-inline">';
			var submit='<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>';
			var reset='<button type="reset" class="layui-btn layui-btn-primary">重置</button>';
			var end ='</div>';
			var formend='</form>';
			var content='';
			$("button#modifyRole").click(function() {
				//获取过滤后的权限
				var roleid = $(this).attr("roleid");
				var rolename = $(this).attr("rolename");
				var input ='<input type="text" name="rolename" required value="'+rolename+'" lay-verify="required" class="layui-input">';
				var hiddenRoleid ='<input type="hidden" name="roleid" value="'+roleid+'">';
				var load= layer.load();
				$.ajax({
					url:"${APP_PATH}/sys/role/getFiltedPermission",
					data:{"roleid":roleid},
					async : false,
					dataType : "json",
					success:function(response,status,xhr)
					{
						
						for(var i=0;i<response.Y.length;i++)
						{
							content+='<input type="checkbox" name="permission" value='+(response.Y)[i].id+' title="'+(response.Y)[i].name+'"checked>';
						}
						for(var i=0;i<response.N.length;i++)
						{
							content+='<input type="checkbox" name="permission" value='+(response.N)[i].id+' title="'+(response.N)[i].name+'">';
						}
						layer.open({
							type : 1,
							title : "权限编辑",
							content : start+hiddenRoleid+item+label+inline+input+end+end+item+plabel+block+content+end+end+item+block+submit+reset+end+end+formend,
							area : [ '600px', '400px' ],
							resize : false,
							cancel: function(){ 
								  content='';
								} 
						});
						layer.close(load);
					},
					error:function()
					{
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
				//重新渲染表单
				form.render();
				form.on('submit(*)',function(){
					return true;
				});
			});
			
			//添加角色
			var start2 ='<form style="margin-top:10px;" class="layui-form" action="${APP_PATH}/sys/role/addRolePermission" method="post">';
			$("#bAddFoods").click(function() {
				var input ='<input type="text" name="rolename" required lay-verify="required" class="layui-input">';
				var load= layer.load();
				$.ajax({
					url:"${APP_PATH}/sys/role/getPermissions",
					async : false,
					dataType : "json",
					success:function(response,status,xhr)
					{
						for(var i=0;i<response.N.length;i++)
						{
							content+='<input type="checkbox" name="permission" value='+(response.N)[i].id+' title="'+(response.N)[i].name+'">';
						}
						layer.open({
							type : 1,
							title : "权限编辑",
							content : start2+item+label+inline+input+end+end+item+plabel+block+content+end+end+item+block+submit+reset+end+end+formend,
							area : [ '600px', '400px' ],
							resize : false,
							cancel: function(){ 
								  content='';
								} 
						});
						layer.close(load);
					},
					error:function()
					{
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
				//重新渲染表单
				form.render();
				form.on('submit(*)',function(){
					return true;
				});
			});
			
			//删除角色
			$("button#delete").click(function() {
				var url = "http://localhost:8080${APP_PATH}/sys/role/delete";
				var id = $(this).attr("roleid");
				doajax(url,id);
			});

			function doajax(url, id) {
				var load = layer.load();
				$.ajax({
					url : url,
					data : {
						roleId : id
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
