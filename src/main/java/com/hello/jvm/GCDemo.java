package com.hello.jvm;

import java.nio.ByteBuffer;

public class GCDemo {
	public static void main(String[] args) throws Exception {
        
        for (int i = 0; i < 1000000; i++) {
            //申请堆外内存，这个内存是本地的直接内存，并非java堆内存
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024*1024*1024);
            System.out.println("created "+i+" byteBuffer");
        }
        
//		byte[] bytes = null;
//		for(int i=0;i<100;i++){
//			bytes = new byte[1024*1024];
//			System.out.println(bytes);
//		}
	}
}
