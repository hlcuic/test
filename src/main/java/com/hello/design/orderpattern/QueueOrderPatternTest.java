package com.hello.design.orderpattern;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 命令模式：将发送者与接受者解耦，将接受者与接收者动作封装成为命令对象，
 * 交给传递者invoker，invoker只需要把命令放置到某个位置，就会触发接受者动作。
 * 命令模式将需要执行的操作封装成对象，启动一个线程将命令对象放入队列，
 * 另一端从队列中向外取数据，然后堆到线程池中执行
 * @author Administrator
 *
 */
public class QueueOrderPatternTest {
	
	private static Queue<IOrder> queue = new LinkedList<IOrder>();
	
	public static void main(String[] args) {
		new Thread(new Runnable(){

			@Override
			public void run() {
				for(int i=0;i<100;i++){
					IOrder order = null;
					if(i%2==0){
						order = new CalcutorOrder();
					}else{
						order = new ReadOrder();
					}
					queue.offer(order);
					try {
						Thread.sleep(100);
						System.out.println("put one order into queue");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}).start();
		
		ExecutorService executors = Executors.newFixedThreadPool(5);
		while(true){
			IOrder order = queue.poll();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			executors.submit(new Runnable(){
				@Override
				public void run() {
					order.execute();
				}
				
			});
		}
		
		
	}
	
	static interface IOrder{
		void execute();
	}
	
	static class CalcutorOrder implements IOrder{

		@Override
		public void execute() {
			System.out.println("计算单元");
		}
		
	}
	
	static class ReadOrder implements IOrder{

		@Override
		public void execute() {
			System.out.println("读取单元");
		}
		
	}
	
}


