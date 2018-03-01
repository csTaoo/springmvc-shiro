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
				<button id="bAddTable" class="layui-btn">添加桌子</button>
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
							<th>桌号</th>
							<th>二维码路径</th>
							<th>创建时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${tables}" var="table" varStatus="i">
							<tr>
								<td>${i.count}</td>
								<td>${table.table_num}</td>
								<td><span>${table.table_vrCode }</span><i id="viewCode" class="layui-icon" style="cursor:pointer;float:right;color: #1E9FFF;">&#xe615</i></td>
								<td>
									${table.create_time}
								</td>
								<td>
									<div class="layui-btn-group">
										<button id="bDeleteTable" tableId ="${table.id }"
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
		layui.use([ 'element', 'jquery', 'layer' ], function() {
			var element = layui.element;
			var layer = layui.layer;
			var $ = layui.jquery;
			
			$("button#bDeleteTable").click(function(){
				
				var tableId = $(this).attr("tableId");
				$.get("${APP_PATH}/table/delete?tableId="+tableId,function(data,status,jqXHR)
				{
					if(data=="删除成功")
					{
						layer.msg(data, {icon: 6});
					}
					else
					{
						layer.msg(data, {icon: 2});
					}
				}).fail(function(jqXHR){
					if(401 == jqXHR.status)
					{
						layer.msg("权限不足", {icon: 2});
					}
					else if(403 == jqXHR.status)
					{
						layer.msg("此功能已被停用", {icon: 2});
					}
				});
			});
			
			$("#bAddTable").click(function(){
				var param = {};
				param.formType=2;
				param.title = "增加桌子";
				layer.prompt(param, function(value, index, elem) {
					var data = {table_num:value,isUpdate:"false"};
					$.post("${APP_PATH}/table/save",data,function(data, textStatus, jqXHR)
					{
						if(data=="保存成功")
						{
							layer.msg(data, {icon: 6});
						}
						else
						{
							layer.msg(data, {icon: 2});
						}
						
					},"html").fail(function(jqXHR){
						if(401 == jqXHR.status)
						{
							layer.msg("权限不足", {icon: 2});
						}
						else if(403 == jqXHR.status)
						{
							layer.msg("此功能已被停用", {icon: 2});
						}
					});
					layer.close(index);
				});
			});
			
			$("i#viewCode").click(function(){
				var path = $(this).prev();
				layer.open({
					  type:1,
					  title: '查看',
					  offset: '100px'
					  ,content: '<img src="/shitao/table/getQRCode?path='+path.text()+'" alt="二维码"/>'
					});     
			});
		});
	</script>
</body>
</html>
