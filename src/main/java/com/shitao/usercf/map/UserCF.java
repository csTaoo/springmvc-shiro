package com.shitao.usercf.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCursor;
import com.shitao.mongodb.operator.OrdersMongoDBOperator;

@Component
public class UserCF {
	
	@Autowired
	private UserMap userMap;
	
	private DataModel matchDataModel()
	{
		OrdersMongoDBOperator operator = new OrdersMongoDBOperator();
		AggregateIterable<Document> list = operator.getCommentData();
		UserMap.init();
		
		MongoCursor<Document> result = list.iterator();
		Document tmpDoc = null;
		File file = new File("tmp.txt");
		System.out.println(file.getAbsolutePath());
		System.out.println(file);
		try {
			file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println(file.getAbsolutePath());
		PrintWriter out =null ;
		try 
		{
			out = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(result.hasNext())
		{
			tmpDoc = result.next();
			Integer userId =  UserMap.getUserInt(tmpDoc.getString("id"));
			String itemIdStr = (String) ((Document)tmpDoc.get("foods")).get("id");
			Long itemId = Long.parseLong(itemIdStr.split("-")[1]);
			Integer value = ((Document)tmpDoc.get("comment")).getInteger("star");
			
			out.write(userId+","+itemId+","+value+"\n");
			
		}
		out.flush();
		out.close();
		result.close();
		DataModel model = null;
		try {
			model = new FileDataModel(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}
	
	public long getRecomend(String userid) throws TasteException
	{
		
		DataModel model = matchDataModel();
		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
		UserNeighborhood neighborhood = new NearestNUserNeighborhood(100, similarity, model); 
		Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		Recommender cachingRecommender = new CachingRecommender(recommender);
		List<RecommendedItem> recommendations = cachingRecommender.recommend(UserMap.getUserInt(userid), 1);
		// 输出推荐结果
		return (recommendations.size() != 0)? recommendations.get(0).getItemID():0;
	}

}
