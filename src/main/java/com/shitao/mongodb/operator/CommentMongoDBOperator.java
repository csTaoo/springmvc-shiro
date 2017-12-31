package com.shitao.mongodb.operator;

import java.util.List;

import org.bson.Document;

import com.alibaba.fastjson.JSON;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.shitao.comment.entity.Comment;
import com.shitao.mongodb.communicate.ConnectMongoDB;

public class CommentMongoDBOperator implements IMongoDBOperater<Comment> {
	
	private MongoClient connect;
	private MongoDatabase database ;
	private MongoCollection<Document> collection;
	
	public CommentMongoDBOperator() {
		
		this.connect = new ConnectMongoDB().newInstance();
		this.database  = connect.getDatabase("test");
		this.collection = database.getCollection("salary");
		
	}
	
	public void insert(Comment e) {
		String orders = JSON.toJSONString(e);
		Document document = Document.parse(orders);
		collection.insertOne(document);
	}

	public void delete(Comment e) {
	}

	public void update(Comment e) {
	}

	public List<Comment> list() {
		return null;
	}

	public Comment get() {
		
		System.out.println(collection.count());
		return null;
	}
	
	
	public void close()
	{
		if(this.connect != null)
		{
			this.connect.close();
		}
	}
}
