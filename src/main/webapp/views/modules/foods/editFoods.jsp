<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<c:set var="APP_PATH" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>餐厅后台管理系统</title>
<link rel="stylesheet"
	href="${APP_PATH}/static/layui/css/layui.css">
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
					action="${APP_PATH}/foods/save" method="post">
					<input type="hidden" name="id" value="${foods.id }" />
					<div class="layui-form-item">
						<label class="layui-form-label">菜品名称</label>
						<div class="layui-input-inline">
							<input type="text" name="food_name" required lay-verify="required"
								value="${foods.food_name}" autocomplete="off"
								class="layui-input" >
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">菜品价格</label>
						<div class="layui-input-inline">
							<input type="text" name="food_price" required lay-verify="required"
								value="${foods.food_price}" autocomplete="off"
								class="layui-input" >
						</div>
					</div>
					<!--  
					<div class="layui-form-item">
						<label class="layui-form-label">菜品数量</label>
						<div class="layui-input-inline">
							<input type="text" name="food_num" required lay-verify="required"
								value="${foods.food_num}" autocomplete="off"
								class="layui-input" >
						</div>
					</div> -->
					<div class="layui-form-item">
						<label class="layui-form-label">菜品图片</label>
						<div class="layui-input-inline">
							<input type="text" name="food_img" 
								placeholder="请输入用户名" value="${foods.food_img}" autocomplete="off"
								class="layui-input" >
						</div>
						<button type="button" id="bAddCarousel" class="layui-btn">选择图片</button>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">菜品折扣</label>
						<div class="layui-input-inline">
							<select name="food_discount">
								<c:forEach var="x" begin="1" end="10">
									<c:choose>
										<c:when test="${fn:contains(foods.food_discount,x) && foods.food_discount ne 1.0 }">
											<option value="0.${x}" selected><c:out value="${x}折"></c:out></option>
										</c:when>
										<c:when test="${x eq 10 && foods.food_discount ne 1.0 }">
											<option value="1.0"><c:out value="无折扣"></c:out></option>
										</c:when>
										<c:otherwise>
											<option value="0.${x}"><c:out value="${x}折"></c:out></option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if test="${foods.food_discount eq 1.0 }">
									<option value="1.0" selected><c:out value="无折扣"></c:out></option>
								</c:if>
							</select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">菜品分类</label>
						<div class="layui-input-inline">
							<select name="foodSort.id">
								<c:forEach items="${foodsort }"  var="food">
									<option value="${food.id}"><c:out value="${food.sort_name }"></c:out></option>
								</c:forEach> 
							</select>
						</div>
					</div>
					<input type="hidden" name="isUpdate" value="${isUpdate }"/>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn" lay-submit lay-filter="foodForm">立即提交</button>
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
	layui.use(['element', 'form', 'upload','jquery'], function(){
	  var element = layui.element;
	  var form = layui.form;
	  var upload = layui.upload;
	  var $ = layui.jquery;
	  form.on('submit(foodForm)', function(data) {
			return true;
		});
	  
	  //执行实例
	  var uploadInst = upload.render({
			elem : '#bAddCarousel' //绑定元素
			,
			url : 'http://localhost:8080${APP_PATH}/foods/upload' //上传接口
			,
			data : {
				time : "time"
			},
			accept : 'images',
			done : function(res) {
				$("#bAddCarousel").prev().children("input").val(res.data.src);
				layer.msg(res.msg, {
					icon : 6
				});
			},
			error : function() {

			}
		});
	});
	</script>
</body>
</html>
