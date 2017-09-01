<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>springmvc-shiro</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
</head>
<body>
<div class="layui-layout layui-layout-admin">
	
  <jsp:include page="../../include/header.jsp"></jsp:include>
  
  <jsp:include page="../../include/left-menu.jsp"></jsp:include>
  
  <!-- 这里是主要内容 -->
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">内容主体区域</div>
  </div>
  
  <jsp:include page="../../include/footer.jsp"></jsp:include>
</div>
<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
  
});
</script>
</body>
</html>
