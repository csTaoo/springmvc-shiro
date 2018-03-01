package shitao;

import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.util.ByteSource;
import org.junit.Assert;
import org.junit.Test;


public class TestPassWord {
	
	@Test
	public void testEncryptPwd()
	{
		String cryptopwd = "ee8be738972687e31bf73db01cc941a90230b46f26527f017099fd2e";
		String pwd = cryptopwd.substring(16);
		ByteSource bs = ByteSource.Util.bytes(cryptopwd.substring(0, 16));
		Sha1Hash sha1 = new Sha1Hash("shitao",bs,2);
		Assert.assertEquals(sha1.toHex(), pwd);
	}

}
