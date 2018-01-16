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
  
  <audio id="media" src="${APP_PATH}/static/audio/mes.mp3" style="display:none;"></audio>
  <!-- 这里是主要内容 -->
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">
	    <div id="daySettle" style="width: 600px;height:400px;"></div>
    </div>
  </div>
  <jsp:include page="../../include/footer.jsp"></jsp:include>
</div>
<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script>
layui.use(['element','jquery','laydate'], function(){
  var element = layui.element;
  var $ = layui.jquery;
  myChart = echarts.init(document.getElementById('daySettle'));
  $.getJSON('${APP_PATH}/sys/getMoneySettleData', function (data) {
	  	function sortData(a,b){
	        
	        return new Date(a._id)>new Date(b._id);
	        
	    }
	    
	    data.sort(sortData);
	    myChart.setOption(option = {
	        title: {
	            text: '营业额统计'
	        },
	        tooltip: {
	            trigger: 'axis'
	        },
	        xAxis: {
	            data: data.map(function (item) {
	                return item._id;
	            })
	        },
	        yAxis: {
	            splitLine: {
	                show: false
	            }
	        },
	        toolbox: {
	            left: 'center',
	            feature: {
	                dataZoom: {
	                    yAxisIndex: 'none'
	                },
	                restore: {},
	                saveAsImage: {}
	            }
	        },
	        dataZoom: [{
	            startValue: '2017-06-01'
	        }, {
	            type: 'inside'
	        }],
	        visualMap: {
	            top: 10,
	            right: 10,
	            pieces: [{
	                gt: 0,
	                lte: 50,
	                color: '#096'
	            }, {
	                gt: 50,
	                lte: 100,
	                color: '#ffde33'
	            }, {
	                gt: 100,
	                lte: 150,
	                color: '#ff9933'
	            }, {
	                gt: 150,
	                lte: 200,
	                color: '#cc0033'
	            }, {
	                gt: 200,
	                lte: 300,
	                color: '#660099'
	            }, {
	                gt: 300,
	                color: '#7e0023'
	            }],
	            outOfRange: {
	                color: '#999'
	            }
	        },
	        series: {
	            name: '营业额',
	            type: 'line',
	            data: data.map(function (item) {
	                return item.value;
	            }),
	            markLine: {
	                silent: true,
	                data: [{
	                    yAxis: 50
	                }, {
	                    yAxis: 100
	                }, {
	                    yAxis: 150
	                }, {
	                    yAxis: 200
	                }, {
	                    yAxis: 300
	                }]
	            }
	        }
	    });
	});
});
</script>
</body>
</html>
