package mongodb;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Ignore;
import org.junit.Test;

import com.shitao.comment.entity.Comment;
import com.shitao.foods.utils.NumberCreater;
import com.shitao.mongodb.operator.CommentMongoDBOperator;
import com.shitao.mongodb.operator.IMongoDBOperater;
import com.shitao.mongodb.operator.OrdersMongoDBOperator;
import com.shitao.mongodb.operator.ShopCarOperator;

public class TestMongoDB {
	
	
	@Test
	@Ignore
	public void testCount()
	{
		IMongoDBOperater<Comment> operator = new CommentMongoDBOperator();
		operator.get();
		Comment comment = new Comment();
		comment.setId("comment");
		comment.setCreate_time(NumberCreater.getCurrentTime());
		comment.setUserId("user");
		comment.setContent("如何评价monogdb");
		operator.insert(comment);
		operator.close();
	}
	
	@Test
	@Ignore
	public void testOrder()
	{
		
		OrdersMongoDBOperator operator = new OrdersMongoDBOperator();
		operator.listNonPayOrder();
	}
	
	@Test
	@Ignore
	public void testShopCar()
	{
		ShopCarOperator operator = new ShopCarOperator();
		InputStream in = TestMongoDB.class.getResourceAsStream("test.txt");
		try {
			byte[] buffer = new byte[in.available()];
			in.read(buffer);
			String s= new String(buffer,"utf-8");
			in.close();
			operator.insert(s);
			System.out.println(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	@Ignore
	public void testOrderAggrevate()
	{
		OrdersMongoDBOperator operator = new OrdersMongoDBOperator();
		System.out.println(operator.getMoneySettleData());
		
	}
	
	@Test
	@Ignore
	public void testGetUserOrders()
	{
		OrdersMongoDBOperator operator = new OrdersMongoDBOperator();
		System.out.println(operator.getUserOrders());
		
	}
	
	@Test
	@Ignore
	public void testCommentOrder()
	{
		OrdersMongoDBOperator operator = new OrdersMongoDBOperator();
		Comment comment = new Comment();
		comment.setContent("好好");
		comment.setStar((short)4);
		comment.setOrderId("0021517145195881");
		operator.commentOrder(comment);
		operator.close();
	}
	
	@Test
	@Ignore
	public void testDelComment()
	{
		OrdersMongoDBOperator operator = new OrdersMongoDBOperator();
		operator.delComment("0021517145195881");
		operator.close();
	}
	
	@Test
	@Ignore
	public void testGetComment()
	{
		OrdersMongoDBOperator operator = new OrdersMongoDBOperator();
		operator.getCommentData();
		operator.close();
	}
	@Test
	public void testGetCommentById()
	{
		OrdersMongoDBOperator operator = new OrdersMongoDBOperator();
		operator.getCommentDataByFoodId("foods-0003");
		operator.close();
	}
	
	
}
