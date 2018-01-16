/*
 * 订单
 */
function Order(data)
{
	//变量声明
	this.id = data.id;
	this.foods = data.items;
	this.money = 0;
	
	//方法声明区
	this.calMoney = Order_calMoney;
	this.checkHadItem = Order_checkHadItem;
	
	this.calMoney();
	
	//方法声明区
	Order.prototype.addItem = function(item)
	{
		if(!this.checkHadItem(item))
		{
			this.foods.push(item);
			//重新计算总金额
			this.money = 0;
			this.calMoney();
		}
		else
		{
			this.addItemAccount(item);
		}
	};
	
	//减少一个订单数量
	Order.prototype.decreaseItemAccount = function(item)
	{
		for(var i=0;i<this.foods.length;i++)
		{
			if(item.id == this.foods[i].id)
			{
				this.foods[i].count -= 1;
			}
		}
		this.calMoney();
		
	};
	
	//增加一个数量
	Order.prototype.addItemAccount = function(item)
	{
		for(var i=0;i<this.foods.length;i++)
		{
			if(item.id == this.foods[i].id)
			{
				this.foods[i].count += 1;
			}
		}
		this.calMoney();
	};
}

//计算总金额
function Order_calMoney()
{
	var itemMoney = 0;
	this.money = 0;
	for(var i=0;i<this.foods.length;i++)
	{
		itemMoney = this.foods[i].count * this.foods[i].money;
		this.money += itemMoney;
	}
}

//判断订单列表中是否已经有食物项
function Order_checkHadItem(item)
{
	var hadItem = false;
	for(var i=0;i<this.foods.length;i++)
	{
		if(item.id == this.foods[i].id)
		{
			hadItem = true;
		}
	}
	return hadItem;
}