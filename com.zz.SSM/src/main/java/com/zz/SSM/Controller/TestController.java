package com.zz.SSM.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zz.SSM.Bean.Customer;
import com.zz.SSM.Service.TestService;
import com.zz.SSM.Util.Logs;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private TestService testService;
	
	/**
	 * 
	 * @Title:pageTest
	 * @Description: TODO(页面测试)
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/htmlTest")
	public String htmlTest(HttpServletRequest request, HttpServletResponse response) {
		return "Test";
	}
	
	/**
	 * 
	 * @Title:pageTest
	 * @Description: TODO(RowBounds 分页)
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/pageTest")
	public void pageTest(HttpServletRequest request, HttpServletResponse response) {
		List<Customer> list = testService.pageTest(new RowBounds(10, 30));
		Logs.info(list.size());
	}
	
	/**
	 * 
	 * @Title:lazyLoad
	 * @Description: TODO(延迟加载)
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/lazyLoadTest")
	public void lazyLoad(HttpServletRequest request, HttpServletResponse response) {
		Customer customer = new Customer();
		customer.setTaskId(new Long("61366"));
		customer.setPhoneNum(new Long("13021525231"));
		
		customer = testService.getCustomerInfo(customer);
	}

}
