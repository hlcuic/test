package com.hello.jvm;

public class SystemDemo {
	public static void main(String[] args) {
		long start1 = System.currentTimeMillis();
		long start2 = System.nanoTime();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end1 = System.currentTimeMillis();
		long end2 = System.nanoTime();
		System.out.println("start1: "+(end1-start1));
		System.out.println("start2: "+(end2-start2)/1000000);
	}
}
