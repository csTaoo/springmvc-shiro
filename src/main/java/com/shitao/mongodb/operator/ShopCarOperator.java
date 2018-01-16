package com.shitao.mongodb.operator;

import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.shitao.common.utils.UserUtils;
import com.shitao.mongodb.communicate.ConnectMongoDB;
import com.shitao.orders.entity.Orders;

public class ShopCarOperator{
	
	private MongoClient connect;
	private MongoDatabase database ;
	private MongoCollection<Document> collection;
	
	public ShopCarOperator() {
		this.connect = new ConnectMongoDB().newInstance();
		this.database  = connect.getDatabase("test");
		this.collection = database.getCollection("shopcar");
	}
	
	
	public void insert(String order) {
		Document document = Document.parse(order);
		collection.insertOne(document);
	}

	public void delete() {
		String userId = UserUtils.gerCurrentUser().getId();
		collection.deleteOne(Filters.eq("id", userId));
	}

	public void update(Orders e) {
	}

	public List<Orders> list() {
		return null;
	}

	public String get() {
		String userId = UserUtils.gerCurrentUser().getId();
		FindIterable<Document> iter = collection.find(Filters.eq("id", userId));
		MongoCursor<Document> i = iter.iterator();
		Document d = null;
		while(i.hasNext())
		{
			d = i.next();
		}
		return d==null?"":d.toJson();
	}

	public void close() {
	}
	

}
