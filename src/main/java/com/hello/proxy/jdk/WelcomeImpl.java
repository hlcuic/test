package com.hello.proxy.jdk;


public class WelcomeImpl implements Iwelcome{

	@Override
	public void sayHello() {
		System.out.println("hello world!!");
	}

	@Override
	public void sayBye() {
		System.out.println("good byte!!");
	}

	@Override
	public void test() {
		System.out.println("test method!!");
	}
	
	
}
