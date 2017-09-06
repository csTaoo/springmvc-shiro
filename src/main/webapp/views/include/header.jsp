<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@	taglib prefix="user" uri="/tlds/user.tld" %>
<div class="layui-header">
	<div class="layui-logo">springmvc-shiro权限管理</div>
	<ul class="layui-nav layui-layout-right">
		<li class="layui-nav-item"><a href="javascript:;">
				${user:getCurrentUserName()} </a>
			<dl class="layui-nav-child">
				<dd>
					<a href="">基本资料</a>
				</dd>
				<dd>
					<a href="">安全设置</a>
				</dd>
			</dl></li>
		<li class="layui-nav-item"><a href="/shitao/logout">退了</a></li>
	</ul>
</div>