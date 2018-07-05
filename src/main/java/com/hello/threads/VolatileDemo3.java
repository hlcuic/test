package com.hello.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决方式2：lock,同步锁，将inc方法加锁，这样每个线程在操作inc时，
 * 就会加锁，其他线程就无法访问该方法，当线程释放锁的时候，主存中的数据已经是修改之后的了，
 * 所以保证了原子性。
 * @author Administrator
 *
 */
public class VolatileDemo3 {
	
	private volatile int num;
	
	Lock lock = new ReentrantLock();
	
	private void inc(){
		lock.lock();
		try {
			num++;
		} finally {
			lock.unlock();
		}
		
	}
	
	public static void main(String[] args) {
		
		final VolatileDemo3 demo = new VolatileDemo3();
		
		for(int i=0;i<10;i++){
			new Thread(new Runnable(){

				@Override
				public void run() {
					for(int i=0;i<1000;i++){
						demo.inc();
					}
				}
				
			}).start();
		}
		
		while(Thread.activeCount()>1){
			Thread.yield();
		}
		
		System.out.println(demo.num);
		
	}
}
