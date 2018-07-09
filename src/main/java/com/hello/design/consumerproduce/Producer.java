package com.hello.design.consumerproduce;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {

	private Queue<String> queue;

	private AtomicInteger atomicInteger = new AtomicInteger();

	public Producer(Queue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {

		while (true) {

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

				if (queue.size() > 10) {
					try {
						queue.notifyAll();
						queue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				int i;
				System.out.println("生产: "+(i=atomicInteger.get()));
				queue.offer(i + "");
			}
			

		}
	}

}
