<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@	taglib prefix="user" uri="/tlds/user.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="APP_PATH" value="${pageContext.request.contextPath }"></c:set>
<div class="layui-header">
	<a href="/shitao/index"><div class="layui-logo">餐厅后台管理系统</div></a>
	<ul class="layui-nav layui-layout-right">
		<li class="layui-nav-item"><a href="javascript:;">
				${user:getCurrentUserName()} </a>
			<dl class="layui-nav-child">
				<dd>
					<a href="${APP_PATH}/sys/user/editpassword">修改密码</a>
				</dd>
			</dl></li>
		<li class="layui-nav-item"><a href="/shitao/logout">退了</a></li>
	</ul>
</div>
<script type="text/javascript">
if(window.location.href.indexOf("/shitao/index") != -1)
{
	localStorage.removeItem('navMark');
}
</script>
