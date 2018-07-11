package com.hello.design.singleinstance;

/**
 * 枚举只有一个对象也是单例模式
 * @author Administrator
 *
 */
public enum SingleInstanceDemo3 {

	SingleInstanceDemo3();
	
	private SingleInstanceDemo3() {
	}

	// 调用时，赋值，如果两个线程同时调用到1位置时，加锁,双重判断,可以使用，但是效率低
	public static SingleInstanceDemo3 getInstance() {
		return SingleInstanceDemo3;
	}

}
