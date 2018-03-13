<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="APP_PATH" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>桌子管理</title>
<link rel="stylesheet" href="${APP_PATH}/static/layui/css/layui.css">
</head>
<body>
	<div class="layui-layout layui-layout-admin">

		<jsp:include page="../../include/header.jsp"></jsp:include>

		<jsp:include page="../../include/left-menu.jsp"></jsp:include>
		
		<!-- 这里是主要内容 -->
		<div class="layui-body">
			<p  style="padding: 15px 15px 0px 15px;">
				<button id="bAddPayCode" class="layui-btn">添加收款码</button>
			</p>
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
							<th>路径</th>
							<th>状态</th>
							<th>创建时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${paycodes}" var="pay" varStatus="i">
							<tr>
								<td>${i.count}</td>
								<td><span>${pay.path}</span><i id="viewCode" class="layui-icon" style="cursor:pointer;float:right;color: #1E9FFF;">&#xe615</i></td>
								<td>
									<c:if test="${pay.status eq 0}">
										启用
									</c:if> <c:if test="${pay.status eq 1}">
										禁用
									</c:if>
								</td>
								<td>
									${pay.time}
								</td>
								<td>
									<div class="layui-btn-group">
										<button id="enable" paycodeId = "${pay.id}"
											class="layui-btn layui-btn-primary layui-btn-small">
											<i class="layui-icon">&#xe642;</i>
										</button>
										<button id="delete" paycodeId ="${pay.id }"
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
		layui.use([ 'element', 'jquery', 'layer','upload' ], function() {
			var element = layui.element;
			var layer = layui.layer;
			var $ = layui.jquery;
			var upload = layui.upload;
			
			//执行实例
			var uploadInst = upload.render({
				elem : '#bAddPayCode' //绑定元素
				,
				url : 'http://localhost:8080${APP_PATH}/paycode/upload' //上传接口
				,
				data : {
					time : "time"
				},
				accept : 'images',
				done : function(res) {

					layer.msg(res.msg, {
						icon : 6
					},function(){
						location.reload();
					});
				},
				error : function(index,upload) {
					console.log(upload);
				}
			});
			
			$("button#enable").click(function() {
				var url = "http://localhost:8080${APP_PATH}/paycode/update";
				var id = $(this).attr("paycodeId");
				doajax(url,id);
			});

			$("button#delete").click(function() {
				var url = "http://localhost:8080${APP_PATH}/paycode/delete";
				var id = $(this).attr("paycodeId");
				doajax(url,id);
			});

			function doajax(url, id) {
				var load = layer.load();
				$.ajax({
					url : url,
					data : {
						paycodeId : id
					},
					async : true,
					success : function(data) {
						layer.close(load);
						if(data == 'success')
						{
							layer.msg('请求成功', {
								icon : 6,
								time : 1000
							},function(){
								location.reload();
							});
						}
						if(data == 'isActive')
						{
							layer.msg('只能启用一张', {
								icon : 2,
								time : 1000
							});
						}
						if(data == 'fail')
						{
							layer.msg('处理失败', {
								icon : 2,
								time : 1000
							});
						}
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
			
			$("i#viewCode").click(function(){
				var path = $(this).prev();
				layer.open({
					  type:1,
					  title: '查看',
					  offset: '100px'
					  ,content: '<img src="/shitao/static/upload/heads/'+path.text()+'" width="300px" heigth="300px" alt="img"/>'
					});     
			});
		});
	</script>
</body>
</html>
