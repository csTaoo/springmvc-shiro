<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>注册</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/user/register" method="post">
		用户名：<input type="text" name="username"/>
		密码：<input type="text" name="password"/>
		<input type="submit" value="注册"/>
	</form>
</body>
</html>