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
<title>轮播管理</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/layui/css/layui.css">
</head>
<body>
	<div class="layui-layout layui-layout-admin">

		<jsp:include page="../../include/header.jsp"></jsp:include>

		<jsp:include page="../../include/left-menu.jsp"></jsp:include>

		<!-- 这里是主要内容 -->
		<div class="layui-body">
			<p style="padding: 15px 15px 0px 15px;">
				<button id="bAddCarousel" class="layui-btn">添加图片</button>
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
							<th>图片路径</th>
							<th>是否启用</th>
							<th>创建时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${carousels}" var="carousel" varStatus="i">
							<tr>
								<td>${i.count}</td>
								<td>${carousel.imgPath}</td>
								<td><c:if test="${carousel.isVaild eq 0}">
										禁用
									</c:if> <c:if test="${carousel.isVaild eq 1}">
										启用
									</c:if></td>
								<td>${carousel.create_time}</td>
								<td>
									<div class="layui-btn-group">
										<button id="enable" carouselId = "${carousel.id}"
											class="layui-btn layui-btn-primary layui-btn-small">
											<i class="layui-icon">&#xe642;</i>
										</button>
										<button id="delete" carouselId = "${carousel.id}"
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
	<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
	<script>
		//JavaScript代码区域
		layui.use([ 'element', 'jquery', 'upload' ], function() {
			var element = layui.element;
			var $ = layui.jquery;
			var upload = layui.upload;
			var layer = layui.layer;

			//执行实例
			var uploadInst = upload.render({
				elem : '#bAddCarousel' //绑定元素
				,
				url : 'http://localhost:8080${APP_PATH}/sys/file/upload' //上传接口
				,
				data : {
					time : "time"
				},
				accept : 'images',
				done : function(res) {

					layer.msg(res.msg, {
						icon : 6
					});
				},
				error : function() {

				}
			});

			$("button#enable").click(function() {
				var url = "http://localhost:8080${APP_PATH}/sys/carousel/update";
				var id = $(this).attr("carouselId");
				doajax(url,id);
			});

			$("button#delete").click(function() {
				var url = "http://localhost:8080${APP_PATH}/sys/carousel/delete";
				var id = $(this).attr("carouselId");
				doajax(url,id);
			});

			function doajax(url, id) {
				var load = layer.load();
				$.ajax({
					url : url,
					data : {
						carouselId : id
					},
					async : true,
					success : function(data) {
						layer.close(load);
						layer.msg('请求成功', {
							icon : 6,
							time : 1000
						});
						location.reload();
					},
					error : function() {
						layer.close(load);
						layer.msg('请求错误', {
							icon : 2,
							time : 1000
						});
					}
				});
			}
		});
	</script>
</body>
</html>