package com.hello.proxy.jdk;

public class WelcomeImpl implements Iwelcome{

	@Override
	public void sayHello() {
		System.out.println("hello world!!");
	}

}
