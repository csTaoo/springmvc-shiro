<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
<style type="text/css">
.header-color
{
	background-color:#19A094;
}
.layui-fluid
{
	margin-top:10em;
}
.header-logo
{
	padding:0.2em;
	font-size: 1.5em;
	color:#C4C5C9;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script type="text/javascript">
	layui.use([ 'form', 'element', 'layer','jquery' ], function() {
		var form = layui.form;
		var element = layui.element;
		var layer = layui.layer;
		var $ = layui.jquery;
		
		$("#btnRegiste").click(function(){
			layer.open({
		  		title: '注册',
		  		btn:['注册','取消'],
			  	content: $("#registePage").html(),
			  	yes:function(){
			  		var name = $("input#registeName").eq(1).val();
			  		var pwd = $("input#registePwd").eq(1).val();
			  		if(name.trim()==='' || pwd.trim() ==='')
			  		{
			  			layer.msg('用户名或密码不能为空', {
		  					icon : 2,
		  					time : 1000
		  				});
			  			return;
			  		}
			  		$.ajax({
			  			url:'${pageContext.request.contextPath}/sys/user/register',
			  			type:'post',
			  			data:{
			  				name:name,
			  				password:pwd
			  			},
			  			success:function(data){
			  				if(data === 'success')
			  				{
			  					layer.msg('注册成功', {
				  					icon : 6,
				  					time : 1000
				  				},function(){
				  					$("input[name='username']").val(name);
					  				$("input[name='password']").val(pwd);
					  				$("#submit").click();
				  				});
			  				}else
			  				{
			  					layer.msg('注册失败', {
				  					icon : 2,
				  					time : 1000
				  				});
			  				}
			  			},
			  			error:function(){
			  				layer.msg('注册失败', {
			  					icon : 2,
			  					time : 1000
			  				});
			  			}
			  		});
			  	},
			});
			
		});
		
		var mes = '${shiroLoginFailure}';
		if (!mes == '' || !mes == undefined)
			layer.msg(mes, {
				icon : 2,
				time : 1000
			});

		//监听提交
		form.on('submit(formDemo)', function(data) {
			return true;
		});
	});
</script>
<title>登录</title>
</head>
<body>
	<div class="main">
		<div class="login">
			<h1 class="htitle"></h1>
			<div class="inset">
				<!--start-main-->
				<form action="${pageContext.request.contextPath}/login" method="post">
			         <div>
						<span><label>用户名</label></span>
						<span><input type="text" name="username" class="textbox" ></span>
					 </div>
					 <div>
						<span><label>密码</label></span>
					    <span><input type="password" name="password" class="password"></span>
					 </div>
					<div class="sign">
                        <input id="submit" type="submit" value="登录" class="submit" />
						<button type="button" id="btnRegiste"class="layui-btn layui-btn-danger layui-btn-xs">注册</a>
					</div>
				</form>
			</div>
			<div id="registePage" style="display:none;">
				<!--start-main-->
		         <div>
					<span><label>用户名</label></span>
					<span><input type="text" id="registeName" class="textbox" ></span>
				 </div>
				 <div>
					<span><label>密码</label></span>
				    <span><input type="password" id="registePwd" class="password"></span>
				 </div>
			</div>
		</div>
	</div>
</body>
</html>