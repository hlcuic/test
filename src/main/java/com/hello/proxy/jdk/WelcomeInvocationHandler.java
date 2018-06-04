package com.hello.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class WelcomeInvocationHandler implements InvocationHandler{
	
	private Object target;
	
	public WelcomeInvocationHandler(Object target){
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("=======before======");
		method.invoke(target, args);
		System.out.println("=======after======");
		return null;
	}

}
