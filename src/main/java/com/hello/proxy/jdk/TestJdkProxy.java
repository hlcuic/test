package com.hello.proxy.jdk;

import java.lang.reflect.Proxy;

public class TestJdkProxy {
	
	public static void main(String[] args) {
		Iwelcome target = new WelcomeImpl();
		Iwelcome proxy = (Iwelcome) getProxy(target);
		proxy.sayHello();
	}
	
	private static Object getProxy(Iwelcome target){
		Object proxy = Proxy.newProxyInstance(Iwelcome.class.getClassLoader(),
				new Class[]{Iwelcome.class},new WelcomeInvocationHandler(target));
		return proxy;
	}
}
