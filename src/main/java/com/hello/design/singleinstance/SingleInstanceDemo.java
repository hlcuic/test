package com.hello.design.singleinstance;

public class SingleInstanceDemo {
	
	//单例模式，饿汉式，保持整个上下文只有一个实例
	private static SingleInstanceDemo demo = new SingleInstanceDemo();
	
	private SingleInstanceDemo(){}
	
	public static SingleInstanceDemo getInstance(){
		return demo;
	}
	
}
