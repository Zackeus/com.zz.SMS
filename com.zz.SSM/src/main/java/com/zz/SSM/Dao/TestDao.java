package com.zz.SSM.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.zz.SSM.Bean.Customer;

public interface TestDao extends CrudDao<Customer> {
	
	public List<Customer> pageTest(RowBounds rowBounds);
	
	public Customer getCustomerInfo(@Param("customer") Customer customer);
	
}
