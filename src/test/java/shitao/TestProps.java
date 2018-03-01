package shitao;

import java.text.MessageFormat;

import org.junit.Test;

import com.shitao.common.utils.PropsUtil;

public class TestProps {

	
	@Test
	public void testGetProp()
	{
		String s = PropsUtil.getProperty("tableUrl");
		String format = MessageFormat.format("http://{0}:8080/shitao/a/index/indexShow?table_num=", s);
		System.out.println(format);
		
	}
}
