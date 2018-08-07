package com.zz.SSM.Bean;

/**
 * 
 * @ClassName: Student
 * @Description: TODO(学生实体)
 * @author zz
 * @date: 2018年8月2日下午7:19:11
 */
public class Student extends DataEntity<Student> {

	private static final long serialVersionUID = 1L;

	private String name;
	private Integer age;
	private String address;

	public Student() {
		super();
	}
	
	public Student(String name, Integer age, String address) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
