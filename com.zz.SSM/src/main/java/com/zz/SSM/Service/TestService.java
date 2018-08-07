package com.zz.SSM.Service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zz.SSM.Bean.Customer;
import com.zz.SSM.Dao.TestDao;

@Service("testService")
public class TestService extends CrudService<TestDao, Customer> {
	
	@Autowired
	private TestDao testDao;
	
	public List<Customer> pageTest(RowBounds rowBounds) {
		return testDao.pageTest(rowBounds);
	}
	
	public Customer getCustomerInfo(Customer customer) {
		return testDao.getCustomerInfo(customer);
	}
	
}
