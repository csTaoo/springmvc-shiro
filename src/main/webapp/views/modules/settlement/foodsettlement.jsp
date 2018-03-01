<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="APP_PATH" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>餐厅后台管理系统</title>
<link rel="stylesheet" href="${APP_PATH}/static/layui/css/layui.css">
<script type="text/javascript" src="${APP_PATH}/static/websocket/stomp.min.js"></script>
<script type="text/javascript" src="${APP_PATH}/static/utils/dateformat.js"></script>
<script type="text/javascript" src="${APP_PATH}/static/echarts/echarts.js"></script>
</head>
<body>
<div class="layui-layout layui-layout-admin">
	
  <jsp:include page="../../include/header.jsp"></jsp:include>
  
  <jsp:include page="../../include/left-menu.jsp"></jsp:include>
  
  <!-- 这里是主要内容 -->
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">
	    <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
		  <ul class="layui-tab-title">
		    <li class="layui-this">日菜品结算</li>
		    <li>月菜品结算</li>
		  </ul>
		  <div class="layui-tab-content">
		  	<div id="nonSolved" class="layui-tab-item layui-show">
		  		<form class="layui-form layui-form-pane" action="">
				    <div class="layui-form-item">
					    <label class="layui-form-label">日期</label>
					    <div class="layui-input-inline">
					      	<input  id="datepicker" type="text" name="title" required  lay-verify="required" placeholder="请选择" autocomplete="off" class="layui-input">
					    </div>
					    <div class="layui-input-inline">
					    	<button id="getSettleData" type="button" class="layui-btn">查看</button>
					    </div>
					 </div>
				</form>
		  		<div id="daySettle" style="width: 1000px;height:400px;"></div>
		  	</div>
	    	<div id="Solved" class="layui-tab-item">
	    		<form class="layui-form layui-form-pane" action="">
				    <div class="layui-form-item">
					    <label class="layui-form-label">月份</label>
					    <div class="layui-input-inline">
					      	<input  id="monthpicker" type="text" name="title" required  lay-verify="required" placeholder="请选择" autocomplete="off" class="layui-input">
					    </div>
					    <div class="layui-input-inline">
					    	<button id="getMonthSettleData" type="button" class="layui-btn">查看</button>
					    </div>
					 </div>
				</form>
	    		<div id="monthSettle" style="width: 1000px;height:400px;"></div>
	    	</div>
		  </div>
		</div>
    </div>
  </div>
  <jsp:include page="../../include/footer.jsp"></jsp:include>
</div>
<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script>
layui.use(['element','jquery','laydate','layer'], function(){
  var element = layui.element;
  var $ = layui.jquery;
  var laydate = layui.laydate;
  var layer = layui.layer;
  
  laydate.render({
    elem: '#datepicker',
    max:new Date().Format("yyyy-MM-dd")
  });
  
  laydate.render({
	    elem: '#monthpicker',
	    type:'month',
	    max:new Date().Format("yyyy-MM")
	  });
  
  getSpecifySettle(new Date().Format("yyyy-MM-dd"));
  getSpecifySettle(new Date().Format("yyyy-MM"));
  
  $("#getSettleData").click(function(){
	  var date = $("#datepicker").val();
	  getSpecifySettle(date);
  });
  
  $("#getMonthSettleData").click(function(){
	  var date = $("#monthpicker").val();
	  getSpecifySettle(date);
  });
  
  myChart = echarts.init(document.getElementById('daySettle'));
  myMonth = echarts.init(document.getElementById('monthSettle'));
	
  
  //获取数据
  function getSpecifySettle(dateArg)
  {
	  var reg = new RegExp("[0-9]{4}-[0|1][1-9]$");
	  var option = 
	  {
		      title: {
		          text: '菜品单日结算'
		      },
		      tooltip: {},
		      legend: {
		          data:['销量','总金额']
		      },
		      xAxis:{
		          data:[]
		      },
		      yAxis: {},
		      series: [{
		          name: '销量',
		          type: 'bar',
		          data: []
		      },{
		          name: '总金额',
		          type: 'bar',
		          data: []
		      }]
		};
	  
	  var monthOption = 
	  {
		      title: {
		          text: '菜品月度结算'
		      },
		      tooltip: {},
		      legend: {
		          data:['销量','总金额']
		      },
		      xAxis:{
		          data:[]
		      },
		      yAxis: {},
		      series: [{
		          name: '销量',
		          type: 'bar',
		          data: []
		      },{
		          name: '总金额',
		          type: 'bar',
		          data: []
		      }]
		};
	  var isMonth = reg.test(dateArg);
	  $.ajax({
		  url:"${APP_PATH}/sys/getSpecifyDaySettle",
		  data:{
			  date:dateArg
		  },
		  async:true,
		  dataType : "json",
		  success:function(data)
		  {
			  // 使用刚指定的配置项和数据显示图表。
			  if(!isMonth)
			  {
				  for(var i=0;i<data.length;i++)
					{
						option.xAxis.data.push(data[i].name);
						option.series[0].data.push(data[i].count);
						option.series[1].data.push(data[i].money);
					}
				  // 使用刚指定的配置项和数据显示图表。
			  	  myChart.setOption(option);
			  }else
			  {
				  for(var i=0;i<data.length;i++)
					{
					  monthOption.xAxis.data.push(data[i].name);
					  monthOption.series[0].data.push(data[i].count);
					  monthOption.series[1].data.push(data[i].money);
					}
				  // 使用刚指定的配置项和数据显示图表。
				  myMonth.setOption(monthOption);
			  }
		  },
		  error:function()
		  {
			  alert("系统错误");
		  },statusCode: {
			    401: function() {
			    	layer.msg('您没有权限', {
						icon : 2,
						time : 1000
					});
			      },
			    403:function()
			    {
			    	layer.msg('此功能已被管理员停用', {
						icon : 2,
						time : 1000
					});
			    }
			 }
	  });
  }
});
</script>
</body>
</html>
