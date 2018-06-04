
package com.hello.threads;

public class VolitileStudy implements Runnable {

	private static boolean isRuning;

	private static void setRunning(boolean isRunning) {
		System.out.println(Thread.currentThread().getName());
		isRuning = isRunning;
	}

	@Override
	public void run() {
		System.out.println("start run...");
		// 线程阻塞
		while (!isRuning) {

		}
		System.out.println("end run...");
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new VolitileStudy());
		t1.start();
		Thread.sleep(3000);
		setRunning(true);
	}
}

