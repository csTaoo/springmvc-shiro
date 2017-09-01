<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.io.StringWriter"%>
<%
Throwable ex = null;
if (request.getAttribute("exception") != null) {
	ex = (Throwable) request.getAttribute("exception");
} else if (request.getAttribute("javax.servlet.error.exception") != null) {
	ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
}

StringWriter stringWriter = new StringWriter();
PrintWriter printWriter = new PrintWriter(stringWriter);
ex.printStackTrace(printWriter);
String error = stringWriter.toString();
stringWriter.close();
printWriter.close();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>错误</title>
</head>
<body>
	<p>不好意思，发生了一点小错误。</p>
	<p>错误代码：500</p>
	<p>错误原因:<p>
	<%=error %>
</body>
</html>