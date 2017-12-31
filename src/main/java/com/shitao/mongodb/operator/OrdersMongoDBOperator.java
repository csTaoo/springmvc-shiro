package com.shitao.mongodb.operator;

import java.util.List;

import org.bson.Document;

import com.alibaba.fastjson.JSON;
import com.mongodb.client.MongoCollection;
import com.shitao.orders.entity.Orders;

public class OrdersMongoDBOperator implements IMongoDBOperater<Orders>{

	private MongoCollection<Document> collection;
	
	public void insert(Orders e) {
		
		String orders = JSON.toJSONString(e);
		Document document = Document.parse(orders);
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

}
