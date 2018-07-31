package com.zz.SSM.Chapter2.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.zz.SSM.Chapter2.impl.HelloWorldImpl;
import com.zz.SSM.Chapter2.intercept.Interceptor;
import com.zz.SSM.Chapter2.service.HelloWorld;

/**
 * 
 * @Title:InterceptorJdkProxy
 * @Description:TODO(JDK动态代理使用拦截器)
 * @Company: 
 * @author zhou.zhang
 * @date 2018年7月31日 下午2:38:52
 */
public class InterceptorJdkProxy implements InvocationHandler {

	private Object target; // 真实对象
	private String interceptorClass = null; // 拦截器权限定名
	
	public InterceptorJdkProxy(Object target, String interceptorClass) {
		this.target = target;
		this.interceptorClass = interceptorClass;
	}
	
	/**
	 * 
	 * @Title:bind
	 * @Description: TODO(绑定委托对象并返回一个代理占位)
	 * @param target 真实对象
	 * @param interceptorClass
	 * @return
	 */
	public static Object bind(Object target, String interceptorClass) {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
				new InterceptorJdkProxy(target, interceptorClass));
	}
	
	/**
	 * @Title:invoke
	 * @Description: TODO(通过代理对象调用方法，首先进入这个方法)
	 * @param proxy 代理对象
	 * @param method 方法，被调用方法
	 * @param args 方法的参数
	 * @return
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (interceptorClass == null) {
			// 没有设置拦截器则直接返回原有方法
			return method.invoke(target, args);
		}
		
		Object result = null;
		// 通过反射生成拦截器
		Interceptor interceptor = (Interceptor) Class.forName(interceptorClass).newInstance();
		
		// 调用前置方法
		if (interceptor.before(proxy, target, method, args)) {
			// 返回原有对象方法
			result = method.invoke(target, args);
		} else {
			// 返回 false 执行 arround 方法
			interceptor.around(proxy, target, method, args);
		}
		
		// 调用后置方法
		interceptor.after(proxy, target, method, args);
		return result;
	}
	
	/**
	 * 
	 * @Title:main
	 * @Description: TODO(测试)
	 * @param args
	 */
	public static void main(String[] args) {
//		HelloWorld proxy = (HelloWorld) InterceptorJdkProxy.bind(new HelloWorldImpl(), "com.zz.SSM.Chapter2.intercept.MyInterceptor");
//		proxy.sayHelloWorld();
		
		/***** 责任链模式 *******/
		HelloWorld proxy1 = (HelloWorld) InterceptorJdkProxy.bind(new HelloWorldImpl(), "com.zz.SSM.Chapter2.intercept.Interceptor1");
		HelloWorld proxy2 = (HelloWorld) InterceptorJdkProxy.bind(proxy1, "com.zz.SSM.Chapter2.intercept.Interceptor2");
		HelloWorld proxy3 = (HelloWorld) InterceptorJdkProxy.bind(proxy2, "com.zz.SSM.Chapter2.intercept.Interceptor3");
		proxy3.sayHelloWorld();
	}

}
