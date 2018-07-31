package com.zz.SSM.Chapter2.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.zz.SSM.Chapter2.impl.HelloWorldImpl;
import com.zz.SSM.Chapter2.service.HelloWorld;

public class JdkProxyExample implements InvocationHandler {
	
	private Object target = null;
	
	public Object bind(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("进入代理逻辑方法");
		System.out.println("在调度真实对象之前的服务");
		Object obj = method.invoke(target, args);
		System.out.println("在调度真实对象之后的服务");
		return obj;
	}
	
	public static void main(String[] args) {
		JdkProxyExample jdk = new JdkProxyExample();
		HelloWorld proxy = (HelloWorld) jdk.bind(new HelloWorldImpl());
		proxy.sayHelloWorld();
	}
}
