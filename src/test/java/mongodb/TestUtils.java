package mongodb;

import java.time.Month;

import org.junit.Assert;
import org.junit.Test;

import com.shitao.common.utils.TimeUtils;

public class TestUtils {
	
	@Test
	public void testMatchMonth()
	{
		String date = "2018-02-04";
		StringBuffer startTime = new StringBuffer();
		StringBuffer endTime = new StringBuffer();
		if(date.matches("[0-9]{4}-[0|1][1-9]"))
		{
			startTime.append(date).append("-1")
			.append(" 00:00:00");
			String[] arr = date.split("-");
			int year = Integer.parseInt(arr[0]);
			boolean isLeapYear = (year%4==0)&&(year%100!=0)||(year%400==0);
			Assert.assertTrue(isLeapYear);
			int days = Month.of(Integer.parseInt(arr[1])).length(isLeapYear);
			endTime.append(date).append("-")
			.append(days)
			.append(" 23:59:59");
		}
		else
		{
			startTime.append(date).append(" 00:00:00");
			endTime.append(date).append(" 23:59:59");
		}
		System.out.println(TimeUtils.formatDate(startTime.toString()));
		
		System.out.println(startTime.toString());
		System.out.println(endTime.toString());
	}

}
