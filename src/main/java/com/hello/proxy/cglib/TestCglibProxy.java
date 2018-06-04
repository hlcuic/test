package com.hello.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;

public class TestCglibProxy {
	
	public static void main(String[] args) {
		WelcomeService proxy = (WelcomeService) getProxy();
		proxy.sayHello();
	}
	
	private static Object getProxy(){
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(WelcomeService.class);
		enhancer.setCallback(new WelcomeInterceptor());
		return enhancer.create();
	}
}
