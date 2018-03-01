package shitao;

import org.apache.mahout.cf.taste.common.TasteException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shitao.usercf.map.UserCF;

public class TestRecommend {
	
	@Test
	public void testGetRecommend()
	{
		UserCF cf =new UserCF();
		try {
			cf.getRecomend("");
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
