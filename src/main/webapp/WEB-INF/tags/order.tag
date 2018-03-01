<%@ tag language="java" pageEncoding="UTF-8"%>
<%@	taglib prefix="user" uri="/tlds/user.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="content" type="java.lang.String" required="true" description="消息内容"%>
<%@ attribute name="type" type="java.lang.String" description="消息类型：info、success、warning、error、loading"%>
<c:set var="APP_PATH" value="${pageContext.request.contextPath}"></c:set>
<!-- 隐藏的上拉框 -->
<div class="am-modal-actions" id="my-actions">
	<div class="am-modal-actions-group">
		<ul class="am-list">
			<li id="liTop" class="am-modal-actions-header">订餐列表</li>
		</ul>
	</div>
	<div class="am-modal-actions-group">
		<button id="pay" class="am-btn am-btn-secondary am-btn-block">下单
		</button>
		<button class="am-btn am-btn-secondary am-btn-block"
			data-am-modal-close>取消</button>
	</div>
</div>
<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">订单确认</div>
    <div class="am-modal-bd" id="orderConfirm">
    </div>
    <div class="am-modal-footer">
      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
      <span class="am-modal-btn" data-am-modal-confirm>确定</span>
    </div>
  </div>
</div>
<script type="text/javascript">
/**
 $.ajax({
	url : "${APP_PATH}/a/index/shopcar",
	async : true,
	dataType : "json",
	success : function(data){
		createLis(data);
	},
	error:function(){
		alert("系统出错");
	}
});*/

//使用h5 localstore
var store = $.AMUI.store;
if (!store.enabled) 
{
	alert('暂不支持localstore');
}
var order = store.get('order');
createLis(order);
$("#pay").click(function(){
	var data = window.shopCar;
	if(data.foods.length == 0)
	{
		alert("请先选择食物项");
		return;
	}
	$("#orderConfirm").html("订单总金额："+window.shopCar.money);
	$('#my-confirm').modal({
        relatedTarget: this,
        onConfirm: function(options) {
        	//设置订单日期
        	var time2 = new Date().Format("yyyy/MM/dd HH:mm:ss");
        	window.shopCar.date = time2;
        	$.ajax({
        		url : "${APP_PATH}/a/index/pay",
        		data:
        		{
        			order:JSON.stringify(window.shopCar)
        		},
        		type:'POST',
        		async : true,
        		success : function(data){
        			alert(data);
        			//将食物数组重设为零
        			if(data!='余额不足')
        			{
        				$("#my-actions").find("li").not("#liTop").remove();
        				window.shopCar.foods = new Array();
        			}
        		},
        		error:function(){
        			alert("系统出错");
        		}
        	});
        },
        onCancel: function() {
        }
    });
});
function createLis(data)
{
	var itemHtml = '<li><a href="javascript:void(0);">'+
    '<span style="float: left"></span>'+
    '<span style="float: right">'+
    '<i id="minus" class="am-icon-minus"></i>'+
    '<i style="font-style:normal;margin-left:10px;margin-right:10px;"></i>'+
    '<i id="plus" class="am-icon-plus"></i></span></a></li>';
	if(data!=null)
	{
		$("#my-actions").find("li").not("#liTop").remove();
		for(var i=0;i<data.foods.length;i++)
		{
			var tempItem = data.foods[i];
			var $templi = $(itemHtml);
			$templi.find("span").eq(0).html(tempItem.name);
			$templi.find("span").attr("fid",tempItem.id);
			$templi.find("i").eq(1).html(tempItem.count);
			$("#my-actions").find("ul").append($templi);
		}
		data.items = data.foods;
		var shopCar = new Order(data);
		shopCar.orderId = '${user:getCurrentUserId()}'+new Date().getTime();
		shopCar.table = store.get("table");
		window.shopCar = shopCar;
	}else
	{
		var $templi = $(itemHtml);
		$templi.find("span").eq(0).html("暂无食品项..");
		$templi.find("i").eq(1).text(0);
		$("#my-actions").find("ul").append($templi);
		var data = 
		{
			id :'${user:getCurrentUserId()}',
			username:'${user:getCurrentUserName()}',
			items :new Array(),
			money : 0,
			table : store.get("table"),
			orderId:'${user:getCurrentUserId()}'+new Date().getTime()
		};
		var shopCar = new Order(data);
		window.shopCar = shopCar;
	}
	initEvent();
};

function initEvent()
{
	var item = {};
	$("i#minus").click(function(){
		
		var fid = $(this).parent("span").prev().attr("fid");
		item.id = fid;
		window.shopCar.decreaseItemAccount(item);
		createLis(window.shopCar);
	});
	
	$("i#plus").click(function(){
		var fid = $(this).parent("span").prev().attr("fid");
		item.id = fid;
		window.shopCar.addItem(item);
		createLis(window.shopCar);
	});
}
</script>