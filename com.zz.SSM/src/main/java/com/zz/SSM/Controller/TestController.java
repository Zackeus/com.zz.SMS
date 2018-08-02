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
	
	@RequestMapping(value = "/data")
	public void testTask(HttpServletRequest request, HttpServletResponse response) {
		List<Customer> list = testService.pageTest(new RowBounds(10, 30));
		Logs.info(list.size());
	}

}
