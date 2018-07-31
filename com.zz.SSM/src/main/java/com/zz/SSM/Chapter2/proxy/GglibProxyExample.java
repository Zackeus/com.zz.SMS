package com.zz.SSM.Chapter2.proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import com.zz.SSM.Chapter2.impl.ReflectServiceImpl;

/**
 * @Title:GglibProxyExample
 * @Description:TODO(CGLIB动态代理)
 * @Company: 
 * @author zhou.zhang
 * @date 2018年7月31日 下午1:31:15
 */
public class GglibProxyExample implements MethodInterceptor {
	
	/**
	 * 
	 * @Title:getProxy
	 * @Description: TODO(生成CGLIB代理对象)
	 * @param cls
	 * @return
	 */
	public Object getProxy(@SuppressWarnings("rawtypes") Class cls) {
		// CGLIB enhancer 增强类对象
		Enhancer enhancer = new Enhancer();
		// 设置增强类型
		enhancer.setSuperclass(cls);
		// 定义代理逻辑对象为当前对象，要求当前对象实现 MethodInterceptor 方法
		enhancer.setCallback(this);
		// 生成并返回代理对象
		return enhancer.create();
	}

	/**
	 * 
	 * @Title:intercept
	 * @Description: TODO(代理逻辑方法)
	 * @param proxy 代理对象
	 * @param method 方法
	 * @param args 方法参数
	 * @param methodProxy 方法代理
	 * @return 代理逻辑返回
	 * @throws Throwable 异常
	 */
	@Override
	public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("调用真实对象前");
		// CGLIB 反射调用真实对象方法
		Object result = methodProxy.invokeSuper(proxy, args);
		System.out.println("调用真实对象后");
		return result;
	}
	
	public static void main(String[] args) {
		GglibProxyExample cpe = new GglibProxyExample();
		ReflectServiceImpl obj = (ReflectServiceImpl) cpe.getProxy(ReflectServiceImpl.class);
		obj.sayHello("张三");
	}
}
