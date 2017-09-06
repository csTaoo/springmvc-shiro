<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="APP_PATH" value="${pageContext.request.contextPath}" scope="page"></c:set>
<div class="layui-side layui-bg-black">
	<div class="layui-side-scroll">
		<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
		<ul class="layui-nav layui-nav-tree" lay-filter="test">
			<li class="layui-nav-item"><a class="" href="javascript:;">系统管理</a>
				<dl class="layui-nav-child">
					<dd>
						<a href="${APP_PATH}/sys/user/userIndex">用户管理</a>
					</dd>
					<dd>
						<a href="${APP_PATH}/sys/role/roleindex">角色管理</a>
					</dd>
					<dd>
						<a href="${APP_PATH}/sys/func/funcIndex">功能管理 </a>
					</dd>
				</dl></li>
		</ul>
	</div>
</div>
