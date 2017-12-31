package mongodb;

import org.junit.Test;

import com.shitao.comment.entity.Comment;
import com.shitao.foods.utils.NumberCreater;
import com.shitao.mongodb.operator.CommentMongoDBOperator;
import com.shitao.mongodb.operator.IMongoDBOperater;

public class TestMongoDB {
	
	
	@Test
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

}
