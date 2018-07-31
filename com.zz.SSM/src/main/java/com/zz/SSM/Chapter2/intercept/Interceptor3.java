package com.zz.SSM.Chapter2.intercept;

import java.lang.reflect.Method;

public class Interceptor3 implements Interceptor {

	@Override
	public boolean before(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("【拦截器3】的 before 方法");
		return true;
	}

	@Override
	public void around(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("【拦截器3】的 around 方法");
	}

	@Override
	public void after(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("【拦截器3】的 after 方法");
	}

}
