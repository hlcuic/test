
package com.hello.threads;

/**
 * 两个线程，线程A和B，线程A执行循环打印操作，然后线程B将状态反转，
 * 线程线程A跳出，大部分情况都会讲线程A执行停止，但是也有可能不能停止，这种
 * 机率比较小，但是一旦发生那么线程A执行就会进入死循环
 * 出现情况：当线程B将status状态的值在高速缓存中修改后，还没有来得及刷新主存时，就去做其他
 * 的事情了。这样线程A就不会读到主内存中status状态的变化
 * @author Administrator
 *
 */
public class VolatileStudy {

	//volatile变量
	private static volatile boolean status = false;

	public static void main(String[] args) throws InterruptedException {
		//线程A执行打印操作，死循环，跳出由线程B改变status状态控制
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				while(!status){
					System.out.println("hello world!");
				}
			}
		},"线程A");
		
		//线程B改变status的状态，从而使线程A执行从死循环中跳出
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				status = true;
			}
		},"线程B");
		
		t1.start();
		
		Thread.sleep(1000);
		t2.start();
		
	}
	
}

