package com.hello.proxy.cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class WelcomeInterceptor implements MethodInterceptor{
	
	@Override
	public Object intercept(Object proxy, Method method, Object[] targetArgs, MethodProxy methodProxy) throws Throwable {
		System.out.println("=====before=====");
		methodProxy.invokeSuper(proxy, targetArgs);
//		method.invoke(AopUtils.getTargetClass(proxy).newInstance(), targetArgs);
		System.out.println("=====after=====");
		return null;
	}

}
