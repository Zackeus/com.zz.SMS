package com.zz.SSM.Dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zz.SSM.Bean.Customer;

public interface TestDao {
	
	public List<Customer> pageTest(RowBounds rowBounds);

}
