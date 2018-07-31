package com.zz.SSM.Chapter2.intercept;

import java.lang.reflect.Method;

public class Interceptor1 implements Interceptor {

	public boolean before(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("【拦截器1】的 before 方法");
		return true;
	}

	public void around(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("【拦截器1】的 around 方法");
	}

	public void after(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("【拦截器1】的 after 方法");
	}

}
