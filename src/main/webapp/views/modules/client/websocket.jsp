<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="APP_PATH" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>websocket</title>
<script type="text/javascript" src="${APP_PATH}/static/websocket/sockjs.min.js"></script>
<script type="text/javascript" src="${APP_PATH}/static/websocket/stomp.min.js"></script>
<script type="text/javascript">
/* 	var socket= new SockJS("${APP_PATH}/myHandler");
	
	socket.onopen=function(frame)
	{
		console.log("已连接上");
	};
	
	
	socket.send("ss"); */
	
	var socket = new WebSocket("ws://localhost:8080${APP_PATH}/myHandler");
	
	socket.onopen=function()
	{
		console.log("已连接");
	};
	
	function sendMessage()
	{
		socket.send("mes");
	}
</script>
</head>
<body>
	测试websocket
	<button onclick="sendMessage()">发送</button>
</body>
</html>