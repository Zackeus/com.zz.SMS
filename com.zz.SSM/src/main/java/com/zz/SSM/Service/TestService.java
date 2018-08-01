package com.zz.SSM.Service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zz.SSM.Bean.Customer;
import com.zz.SSM.Dao.TestDao;

@Service("testService")
@Transactional(readOnly = true)
public class TestService {
	
	@Autowired
	private TestDao testDao;
	
	public List<Customer> pageTest(RowBounds rowBounds) {
		return testDao.pageTest(rowBounds);
	}

}
