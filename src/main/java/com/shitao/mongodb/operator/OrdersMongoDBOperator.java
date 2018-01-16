package com.shitao.mongodb.operator;

import java.time.Month;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

import com.alibaba.fastjson.JSON;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MapReduceIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.shitao.common.utils.TimeUtils;
import com.shitao.common.utils.UserUtils;
import com.shitao.mongodb.communicate.ConnectMongoDB;
import com.shitao.orders.entity.Orders;

public class OrdersMongoDBOperator implements IMongoDBOperater<Orders>{

	
	private MongoClient connect;
	private MongoDatabase database ;
	private MongoCollection<Document> collection;
	
	public OrdersMongoDBOperator() {
		this.connect = new ConnectMongoDB().newInstance();
		this.database  = connect.getDatabase("test");
		this.collection = database.getCollection("order");
	}
	
	public void insert(String order) {
		Document document = Document.parse(order);
		document.append("date", new Date());
		collection.insertOne(document);
	}

	public void delete(Orders e) {
	}

	public void update(Orders e) {
	}

	public List<Orders> list() {
		return null;
	}

	public Orders get() {
		return null;
	}

	public void close() {
	}
	
	public void listNonPayOrder()
	{
		String userId = UserUtils.gerCurrentUser().getId();
		FindIterable<Document> iter = collection.find(Filters.eq("id", userId));
		MongoCursor<Document> i = iter.iterator();
		while(i.hasNext())
		{
			Document  d = i.next();
			System.out.println(d.toJson());
		}
	}

	public void insert(Orders e) {
		
		
	}
	
	public String listSpecifyDayData(String date)
	{
		StringBuffer startTime = new StringBuffer();
		StringBuffer endTime = new StringBuffer();
		if(date.matches("[0-9]{4}-[0|1][1-9]"))
		{
			startTime.append(date).append("-1")
			.append(" 00:00:00");
			String[] arr = date.split("-");
			int year = Integer.parseInt(arr[0]);
			boolean isLeapYear = (year%4==0)&&(year%100!=0)||(year%400==0);
			int days = Month.of(Integer.parseInt(arr[1])).length(isLeapYear);
			endTime.append(date).append("-")
			.append(days)
			.append(" 23:59:59");
		}
		else
		{
			startTime.append(date).append(" 00:00:00");
			endTime.append(date).append(" 23:59:59");
		}
		List<BasicDBObject> list = new LinkedList<BasicDBObject>();
		
		BasicDBObject unwind = new BasicDBObject();
		unwind.put("$unwind", "$foods");
		
		BasicDBObject match = new BasicDBObject();
		BasicDBObject childMatch = new BasicDBObject();
		childMatch.put("$gte", TimeUtils.formatDate(startTime.toString()));
		childMatch.append("$lte", TimeUtils.formatDate(endTime.toString()));
		match.put("$match",new BasicDBObject("date",childMatch));
		BasicDBObject group = new BasicDBObject();
		group.put("$group", new BasicDBObject("_id","$foods.id")
		.append("name", new BasicDBObject("$last","$foods.name"))
		.append("count", new BasicDBObject("$sum","$foods.count"))
		.append("money", new BasicDBObject("$sum","$foods.money")));
		
		list.add(unwind);
		list.add(match);
		list.add(group);
		AggregateIterable<Document> iter = collection.aggregate(list);
		MongoCursor<Document> i = iter.iterator();
		
		List<Document> doclist = new LinkedList<Document>();
		while(i.hasNext())
		{
			doclist.add(i.next());
		}
		return JSON.toJSONString(doclist);
	}
	
	
	public String getMoneySettleData()
	{
		StringBuffer mapFunction = new StringBuffer();
		mapFunction.append("function(){var date = this.date;")
		.append("var date = this.date;").append("var year = date.getFullYear();")
		.append("var month = date.getMonth()+1;").append("var myDate = date.getDate();")
		.append("var day = year+\"-\"+month+\"-\"+myDate;")
		.append("emit(day,this.money);}");
		
		StringBuffer reduceFunction = new StringBuffer();
		reduceFunction.append("function(key,values){return Array.sum(values);}");
		MapReduceIterable<Document> iter=collection.mapReduce(mapFunction.toString(), reduceFunction.toString());
		Iterator<Document> i = iter.iterator();
		List<Document> doclist = new LinkedList<Document>();
		while(i.hasNext())
		{
			doclist.add(i.next());
		}
		return JSON.toJSONString(doclist);
	}
	
	
}
