package com.hello.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
	public static void main(String[] args) {
		test1();
	}
	
	private static void test1(){
		Lock lock = new ReentrantLock();
		Station station = new Station(lock);
		for(int i=0;i<1000;i++){
			new Thread(station).start();
		}
	}
}

class Station implements Runnable{
	
	private Lock lock;
	
	int total = 1000;
	
	Station(Lock lock){
		this.lock = lock;
	}
	
	@Override
	public void run() {
		decrease();
	}
	
	private void decrease(){
		lock.lock();
		try {
			if(total>0){
				total--;
			}
			System.out.println("剩余票数:"+total);
		} finally {
			lock.unlock();
		}
		
	}
	
}
