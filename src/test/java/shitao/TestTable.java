package shitao;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shitao.foods.utils.NumberCreater;
import com.shitao.table.entity.Table;
import com.shitao.table.service.TableService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-context.xml"})
public class TestTable {

	@Autowired
	private TableService tableService;
	
	@Test
	@Ignore
	public void testSave()
	{
		Table table = new Table();
		table.setId("table-0001");
		table.setTable_num(1);
		table.setTable_status((short) 1);
		table.setTable_vrCode("vrcode");
		table.setCreate_time(NumberCreater.getCurrentTime());
		
		tableService.save(table);
		
	}
	
	@Test
	@Ignore
	public void testGetAndUpdate()
	{
		
		Table table = tableService.get("table-0001");
		Assert.assertEquals("table-0001", table.getId());
		table.setTable_status((short) 2);
		tableService.update(table);
		
	}
}
