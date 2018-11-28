package com.hello.threads;

public class ThreadLocalTest {
	public static void main(String[] args) {
		new Thread(new Runnable(){

			@Override
			public void run() {
				ThreadLocalStudy.add("1","1");
				ThreadLocalStudy.clear();
				ThreadLocalStudy.print();
			}
			
		}).start();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				ThreadLocalStudy.add("2","2");
				ThreadLocalStudy.print();
			}
			
		}).start();
		
		System.out.println(ThreadLocalStudy.threadlocal);
	}
}
