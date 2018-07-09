package com.hello.design.consumerproduce;

import java.util.Queue;

public class Consumer implements Runnable {

	private Queue<String> queue;

	public Consumer(Queue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			String s = queue.poll();
			System.out.println("消费： " + s);
		}

	}

}
