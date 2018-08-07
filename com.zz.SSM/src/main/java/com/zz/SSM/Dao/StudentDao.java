package com.zz.SSM.Dao;

import java.util.List;

import com.zz.SSM.Bean.Student;

public interface StudentDao extends CrudDao<Student> {
	
	 List<Student> getStudents();
	 
}
