package com.zz.SSM.Util;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @Title:Exceptions
 * @Description:TODO(关于异常的工具类.)
 * @Company: 
 * @author zhou.zhang
 * @date 2018年8月7日 上午10:48:17
 */
public class Exceptions {

	/**
	 * 
	 * @Title：unchecked
	 * @Description: TODO(将CheckedException转换为UncheckedException.)
	 * @see：
	 * @param e
	 * @return
	 */
	public static RuntimeException unchecked(Exception e) {
		if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		} else {
			return new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @Title：getStackTraceAsString
	 * @Description: TODO(将ErrorStack转化为String.)
	 * @see：
	 * @param e
	 * @return
	 */
	public static String getStackTraceAsString(Throwable e) {
		if (e == null){
			return "";
		}
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}

	/**
	 * 
	 * @Title：isCausedBy
	 * @Description: TODO(判断异常是否由某些底层的异常引起.)
	 * @see：
	 * @param ex
	 * @param causeExceptionClasses
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isCausedBy(Exception ex, Class<? extends Exception>... causeExceptionClasses) {
		Throwable cause = ex.getCause();
		while (cause != null) {
			for (Class<? extends Exception> causeClass : causeExceptionClasses) {
				if (causeClass.isInstance(cause)) {
					return true;
				}
			}
			cause = cause.getCause();
		}
		return false;
	}

	/**
	 * 
	 * @Title：getThrowable
	 * @Description: TODO(在request中获取异常类)
	 * @see：
	 * @param request
	 * @return
	 */
	public static Throwable getThrowable(HttpServletRequest request){
		Throwable ex = null;
		if (request.getAttribute("exception") != null) {
			ex = (Throwable) request.getAttribute("exception");
		} else if (request.getAttribute("javax.servlet.error.exception") != null) {
			ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
		}
		return ex;
	}
	
}
