package shitao;

import java.sql.Date;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.shitao.qrcode.factory.QRCodeFactory;

public class TestUtils {
	
	@Test
	@Ignore
	public void testCreateQRCode()
	{
		
		QRCodeFactory fac = new QRCodeFactory();
		String path = fac.createQRcode("你好 二维码", "hello");
		Assert.assertNotNull(path);
		
	}
	@Test
	public void testPath()
	{
		Date d = new Date(System.currentTimeMillis());
		
	}
}
