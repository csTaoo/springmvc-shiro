package shitao;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shitao.common.paginationer.PageHelper;
import com.shitao.foods.entity.FoodSort;
import com.shitao.foods.entity.Foods;
import com.shitao.foods.service.FoodsService;
import com.shitao.foods.utils.NumberCreater;
import com.shitao.sys.entity.User;
import com.shitao.sys.service.SystemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-context.xml"})
public class TestFoods {
	
	@Autowired
	private FoodsService foodsService;
	
	@Autowired
	private SystemService systemService;
	@Test
	@Ignore
	//事务回滚
	@Rollback(true)
	public void testInsert()
	{
		Foods food = new Foods();
		food.setId("foods-0002");
		food.setFood_name("水煮鱼");
		food.setFood_price(100);
		food.setFood_discount(1);
		
		FoodSort sort = new FoodSort();
		sort.setId("sort-0001");
		
		food.setFoodSort(sort);
		
		foodsService.save(food);
		
	}
	
	@Test
	@Ignore
	public void testGetLastesNum()
	{
		String num = foodsService.getLastestNumber();
		String generateNum = NumberCreater.generateNumber("foods", num);
		Assert.assertEquals("foods-0003", generateNum);
	}
	
	
	@Test
	@Ignore
	public void testListFoods()
	{
		PageHelper.startPage(1);
		List<Foods> foods = foodsService.list();
		PageHelper.endPage();
		
	}
	
	@Test
	@Ignore
	public void testGetUser()
	{
		User user = systemService.getUserByName("shitao");
		System.out.println(user.getPassword());
	}
	
	
	@Test
	@Ignore
	public void testUpdateFood()
	{
		
		Foods f = foodsService.get("foods-0001");
		
		f.setFood_num(10);
		
		foodsService.update(f);
		
	}
	
	@Test
	public void testreply()
	{
		System.out.println(NumberCreater.replyFoodId(1));
		
		
	}
}
