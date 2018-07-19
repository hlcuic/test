package com.hello.jvm;

public class HeapDemo {
	public static void main(String[] args) {
		System.out.println("最大堆："+Runtime.getRuntime().maxMemory());
		System.out.println("空闲堆："+Runtime.getRuntime().freeMemory());
		System.out.println("总的堆："+Runtime.getRuntime().totalMemory());
	}
}
