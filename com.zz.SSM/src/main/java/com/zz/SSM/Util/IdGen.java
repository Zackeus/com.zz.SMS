package com.zz.SSM.Util;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.UUID;

import org.activiti.engine.impl.cfg.IdGenerator;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 
 * @Title:IdGen
 * @Description:TODO(封装各种生成唯一性ID算法的工具类.)
 * @Company: 
 * @author zhou.zhang
 * @date 2018年8月7日 上午10:57:58
 */
@Service
@Lazy(false)
public class IdGen implements IdGenerator, SessionIdGenerator {

	private static SecureRandom random = new SecureRandom();
	
	/**
	 * 
	 * @Title：uuid
	 * @Description: TODO(封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.)
	 * @see：
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 
	 * @Title：randomLong
	 * @Description: TODO(使用SecureRandom随机生成Long.)
	 * @see：
	 * @return
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	/**
	 * 
	 * @Title：randomBase62
	 * @Description: TODO(基于Base62编码的SecureRandom随机生成bytes.)
	 * @see：
	 * @param length
	 * @return
	 */
	public static String randomBase62(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return Encodes.encodeBase62(randomBytes);
	}
	
	/**
	 * Activiti ID 生成
	 */
	@Override
	public String getNextId() {
		return IdGen.uuid();
	}

	@Override
	public Serializable generateId(Session session) {
		return IdGen.uuid();
	}
	
	/**
	 * 测试
	 */
	public static void main(String[] args) {
		System.out.println(IdGen.uuid());
		System.out.println(IdGen.uuid());
		System.out.println(IdGen.uuid().length());
		System.out.println(new IdGen().getNextId());
		for (int i=0; i<1000; i++){
			System.out.println(IdGen.randomLong() + "  " + IdGen.randomBase62(5));
		}
	}

}
