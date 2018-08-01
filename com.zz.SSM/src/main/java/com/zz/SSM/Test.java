package com.zz.SSM;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zz.SSM.Bean.Customer;
import com.zz.SSM.Service.TestService;

public class Test {
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/spring-*.xml");
		TestService testService = (TestService) context.getBean("testService");
		List<Customer> list = testService.pageTest(new RowBounds(10, 30));
		System.out.println(list.size());
	}

}
