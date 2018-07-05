package com.hello.threads;

/**
 * 测试volatile是否能够保证原子性,
 * 经过测试，大部分情况num的最终结果是小于10000的数字，启动10个线程，
 * 每个线程执行加1操作，每个线程在高速缓存中计算后，然后将数值刷新到主存中,会出现以下情况：
 * 主存num为0，当线程1计算后，num = 1，还没有写入主存，线程2抢到cpu的时间片，从主存读取数据
 * num=0，然后执行加1，写入高速缓存，刷新主存，主存数据num=1,然后线程1又抢到时间片，将num=1
 * 刷新到主存，这样执行两次加1，但是num只是加1，这样就存在问题了。
 * @author Administrator
 *
 */
public class VolatileDemo1 {
	
	private volatile int num;
	
	private void inc(){
		num++;
	}
	
	public static void main(String[] args) {
		
		final VolatileDemo1 demo = new VolatileDemo1();
		
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
