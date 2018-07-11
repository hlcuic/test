package com.hello.design.singleinstance;

public class SingleInstanceDemo2 {

	// 懒汉式，定义变量类型时不进行初始化，获取单例实例时在赋值
	private static SingleInstanceDemo2 demo;

	private static Object lock = new Object();

	private SingleInstanceDemo2() {
	}

	// 调用时，赋值，如果两个线程同时调用到1位置时，加锁,双重判断,可以使用，但是效率低
	public static SingleInstanceDemo2 getInstance() {
		if (demo == null) {
			// 1位置
			synchronized (lock) {
				if(demo == null){
					demo = new SingleInstanceDemo2();
				}
			}
		}
		return demo;
	}

}
