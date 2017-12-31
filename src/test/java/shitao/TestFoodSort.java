package shitao;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shitao.foods.entity.FoodSort;
import com.shitao.foods.service.FoodSortService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-context.xml"})
public class TestFoodSort {
	
	@Autowired
	private FoodSortService foodSortService;
	
	@Test
	public void testListFoodSort()
	{
		
		List<FoodSort> list = foodSortService.list();
		Iterator<FoodSort> i = list.iterator();
		while(i.hasNext())
		{
			
		}
		
		
	}

}
