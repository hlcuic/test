package com.hello.threads;

/**
 * 解决方式1：synchronized,同步锁，将inc方法加锁，这样每个线程在操作inc时，
 * 就会加锁，其他线程就无法访问该方法，当线程释放锁的时候，主存中的数据已经是修改之后的了，
 * 所以保证了原子性。
 * @author Administrator
 *
 */
public class VolatileDemo2 {
	
	private volatile int num;
	
//	private synchronized void inc(){
//		num++;
//	}
	
	private void inc(){
		synchronized(this){
			num++;
		}
	}
	
	public static void main(String[] args) {
		
		final VolatileDemo2 demo = new VolatileDemo2();
		
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
