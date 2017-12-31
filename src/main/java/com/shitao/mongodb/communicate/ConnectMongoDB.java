package com.shitao.mongodb.communicate;

import com.mongodb.MongoClient;

public class ConnectMongoDB {
	
	private final static String  MONGODB_HOST= "localhost";
	
	public MongoClient newInstance()
	{
		MongoClient mongoClient = new MongoClient(MONGODB_HOST, 27017);
		return mongoClient;
	}
}
