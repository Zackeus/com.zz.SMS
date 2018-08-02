package com.zz.SSM.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zz.SSM.Bean.Student;
import com.zz.SSM.Dao.StudentDao;
import com.zz.SSM.Util.Logs;

@Service("studentService")
@Transactional(readOnly = true)
public class StudentService {
	
	/**
	 * @Cacheable 支持如下几个参数：
	 * value：缓存位置名称，不能为空，如果使用EHCache，就是ehcache.xml中声明的cache的name
	 * key：缓存的key，默认为空，既表示使用方法的参数类型及参数值作为key，支持SpEL
	 * condition：触发条件，只有满足条件的情况才会加入缓存，默认为空，既表示全部都加入缓存，支持SpEL
	 * 
	 */
	
	/**
	 * @CacheEvict 支持如下几个参数：
	 * value：缓存位置名称，不能为空，同上
	 * key：缓存的key，默认为空，同上
	 * condition：触发条件，只有满足条件的情况才会清除缓存，默认为空，支持SpEL
	 * allEntries：true表示清除value中的全部缓存，默认为false
	 */
	
	@Autowired
	private StudentDao studentDao;
	
	@Cacheable(value = {"studentCache"})
	public List<Student> getStudents() {
		return studentDao.getStudents();
	}
	
	@CacheEvict(value = {"studentCache"},allEntries = true)
	public void clearCache() {
		Logs.info("清除缓存......");
	}
}
