package com.shitao.sys.security.password;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

public class EncryptionPassword{

	/**
	 * 使用md5加密
	 * 2017年8月25日
	 * @param password
	 * @return
	 * author：shitao.Chen
	 */
	public static String encryptionMD5(String password) {
		//生成随机数作为salt加密
		RandomNumberGenerator randomNumber = new SecureRandomNumberGenerator();
		String salt = randomNumber.nextBytes(8).toHex();
		
		//进行sha-1加密
		SimpleHash passwordHash = new SimpleHash("SHA-1", password, salt, 2);
		
		
		return salt+passwordHash.toHex();
	}
	

}