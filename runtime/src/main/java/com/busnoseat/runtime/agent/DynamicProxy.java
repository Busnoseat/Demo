package com.busnoseat.runtime.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public abstract class DynamicProxy<T> {
	protected static Logger logger = LoggerFactory
			.getLogger(DynamicProxy.class);

	@SuppressWarnings("unchecked")
	public static <T> T newProxyInstance(ClassLoader loader,
			Class<?>[] interfaces, InvocationHandler h) {
		T resultObj = null;
		try {
			resultObj = (T) Proxy.newProxyInstance(loader, interfaces, h);
		} catch (final IllegalArgumentException e) {
			logger.info("执行校验数据出现异常,请检查数据完整性");
			throw new IllegalArgumentException(e);
		}
		return resultObj;
	}
	 
}
