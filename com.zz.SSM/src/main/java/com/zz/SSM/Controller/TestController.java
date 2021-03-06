package com.zz.SSM.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zz.SSM.Bean.Customer;
import com.zz.SSM.Bean.Page;
import com.zz.SSM.Bean.Student;
import com.zz.SSM.Service.StudentService;
import com.zz.SSM.Service.TestService;
import com.zz.SSM.Util.Logs;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private StudentService studentService;
	
	/**
	 * 
	 * @Title:pageTest
	 * @Description: TODO(页面测试)
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/htmlTest")
	public String htmlTest(HttpServletRequest request, HttpServletResponse response, Model model) {
		Customer customer = new Customer();
		customer.setTaskId(new Long("61366"));
		customer.setPhoneNum(new Long("13021525231"));
		model.addAttribute("customer", testService.getCustomerInfo(customer));
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
		Page<Customer> page = testService.findPage(new Page<>(10, 30), new Customer());
		
		Logs.info("总条数：" + page.getTotal());
		Logs.info("总页数：" + page.getTotalPage());
		Logs.info("查询条数:" + page.getList().size());
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
		Logs.info(customer);
	}
	
	/**
	 * 
	 * @Title:firstCacheTest
	 * @Description: TODO(测试二级缓存)
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/cacheTest")
	public void cacheTest(HttpServletRequest request, HttpServletResponse response) {
		Customer customer = new Customer();
		customer.setTaskId(new Long("61366"));
		customer.setPhoneNum(new Long("13021525231"));
		
		Customer customer2 = testService.getCustomerInfo(customer);
		Logs.info(customer2);
		Customer customer3 = testService.getCustomerInfo(customer);
		Logs.info(customer3);
	}
	
	/**
	 * 
	 * @Title: encacheTest
	 * @Description: TODO(encache缓存测试)
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/encacheTest")
	public void encacheTest(HttpServletRequest request, HttpServletResponse response) {
		List<Student> list = studentService.getStudents();
		for(Student student: list) {
			Logs.info(student);
		}
	}
	
	/**
	 * 
	 * @Title: clearCacheTest
	 * @Description: TODO(清除缓存测试)
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/clearCacheTest")
	public void clearCacheTest(HttpServletRequest request, HttpServletResponse response) {
		studentService.clearCache();
	}
	
	/**
	 * 
	 * @Title: clearCacheTest
	 * @Description: TODO(更新保存)
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/saveTest")
	public void saveTest(HttpServletRequest request, HttpServletResponse response) {
		Student student  = new Student("童漠然", 18, "南京");
		studentService.save(student);
	}
	
}
