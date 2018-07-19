package com.hello.jvm;

public class GCDemo {
	public static void main(String[] args) {
		byte[] bytes = null;
		for(int i=0;i<100;i++){
			bytes = new byte[1024*1024];
			System.out.println(bytes);
		}
	}
}
