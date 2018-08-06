package com.zz.SSM.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * 
 * @Title:PropertiesLoader
 * @Description:TODO(Properties文件载入工具类. 可载入多个properties文件, 相同的属性在最后载入的文件中的值将会覆盖之前的值，但以System的Property优先.)
 * @Company: 
 * @author zhou.zhang
 * @date 2018年8月6日 下午6:16:51
 */
public class PropertiesLoader {
	
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	private final Properties properties;

	public PropertiesLoader(String... resourcesPaths) {
		properties = loadProperties(resourcesPaths);
	}

	public Properties getProperties() {
		return properties;
	}

	/**
	 * 
	 * @Title:getValue
	 * @Description: TODO(取出Property，但以System的Property优先,取不到返回空字符串.)
	 * @param key
	 * @return
	 */
	private String getValue(String key) {
		String systemProperty = System.getProperty(key);
		if (systemProperty != null) {
			return systemProperty;
		}
		if (properties.containsKey(key)) {
	        return properties.getProperty(key);
	    }
	    return "";
	}

	/**
	 * 
	 * @Title:getProperty
	 * @Description: TODO(取出String类型的Property，但以System的Property优先,如果都为Null则抛出异常.)
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		String value = getValue(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return value;
	}

	/**
	 * 
	 * @Title:getProperty
	 * @Description: TODO(取出String类型的Property，但以System的Property优先.如果都为Null则返回Default值.)
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String getProperty(String key, String defaultValue) {
		String value = getValue(key);
		return value != null ? value : defaultValue;
	}

	/**
	 * 
	 * @Title:getInteger
	 * @Description: TODO(取出Integer类型的Property，但以System的Property优先.如果都为Null或内容错误则抛出异常)
	 * @param key
	 * @return
	 */
	public Integer getInteger(String key) {
		String value = getValue(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Integer.valueOf(value);
	}

	/**
	 * 
	 * @Title:getInteger
	 * @Description: TODO(取出Integer类型的Property，但以System的Property优先.如果都为Null则返回Default值，如果内容错误则抛出异常)
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public Integer getInteger(String key, Integer defaultValue) {
		String value = getValue(key);
		return value != null ? Integer.valueOf(value) : defaultValue;
	}

	/**
	 * 
	 * @Title:getDouble
	 * @Description: TODO(取出Double类型的Property，但以System的Property优先.如果都为Null或内容错误则抛出异常.)
	 * @param key
	 * @return
	 */
	public Double getDouble(String key) {
		String value = getValue(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Double.valueOf(value);
	}

	/**
	 * 
	 * @Title:getDouble
	 * @Description: TODO(取出Double类型的Property，但以System的Property优先.如果都为Null则返回Default值，如果内容错误则抛出异常)
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public Double getDouble(String key, Integer defaultValue) {
		String value = getValue(key);
		return value != null ? Double.valueOf(value) : defaultValue;
	}

	/**
	 * 
	 * @Title:getBoolean
	 * @Description: TODO(取出Boolean类型的Property，但以System的Property优先.如果都为Null抛出异常,如果内容不是true/false则返回false.)
	 * @param key
	 * @return
	 */
	public Boolean getBoolean(String key) {
		String value = getValue(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Boolean.valueOf(value);
	}

	/**
	 * 
	 * @Title:getBoolean
	 * @Description: TODO(取出Boolean类型的Property，但以System的Property优先.如果都为Null则返回Default值,如果内容不为true/false则返回false.)
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public Boolean getBoolean(String key, boolean defaultValue) {
		String value = getValue(key);
		return value != null ? Boolean.valueOf(value) : defaultValue;
	}

	/**
	 * 
	 * @Title:loadProperties
	 * @Description: TODO(载入多个文件, 文件路径使用Spring Resource格式.)
	 * @param resourcesPaths
	 * @return
	 */
	private Properties loadProperties(String... resourcesPaths) {
		Properties props = new Properties();

		for (String location : resourcesPaths) {

			InputStream is = null;
			try {
				Resource resource = resourceLoader.getResource(location);
				is = resource.getInputStream();
				props.load(is);
			} catch (IOException ex) {
				Logs.info("Could not load properties from path:" + location + ", " + ex.getMessage());
			} finally {
				IOUtils.closeQuietly(is);
			}
		}
		return props;
	}

}
