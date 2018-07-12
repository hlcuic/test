package com.hello.design.singleinstance;

/**
 * 内部类方式实现单例
 * @author Administrator
 *
 */
public class SingleInstanceDemo4 {

	private SingleInstanceDemo4() {
	}
	
	private static class SingleInstance{
		private static SingleInstanceDemo4 instance = new SingleInstanceDemo4();
	}

	public static SingleInstanceDemo4 getInstance() {
		return SingleInstance.instance;
	}

}
