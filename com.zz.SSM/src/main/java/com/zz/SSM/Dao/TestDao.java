package com.zz.SSM.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.zz.SSM.Bean.Customer;
import com.zz.SSM.Bean.Page;

public interface TestDao<T> {
	
	public List<Customer> pageTest(RowBounds rowBounds);
	
	public Customer getCustomerInfo(@Param("customer") Customer customer);
	
	public List<T> getCustomers(@Param("page") Page<T> page, @Param("customer") Customer customer);

}
