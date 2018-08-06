package com.zz.SSM.Config;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.zz.SSM.Util.PropertiesLoader;

/**
 * 
 * @Title:Global
 * @Description:TODO(全局配置参数)
 * @Company:
 * @author zhou.zhang
 * @date 2018年8月6日 下午4:55:25
 */
@Component
public class Global {
	
	/**
	 * 当前对象实例
	 */
	private static Global global = new Global();
	
	/**
	 * 全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();
	
	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader("properties/jdbc.properties");
	
	/**
	 * 
	 * @Title:getInstance
	 * @Description: TODO(获取当前对象实例)
	 * @return
	 */
	public static Global getInstance() {
		return global;
	}
	
	/**
	 * 
	 * @Title:getConfig
	 * @Description: TODO(获取配置)
	 * @param key
	 * @return
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = loader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}

}
