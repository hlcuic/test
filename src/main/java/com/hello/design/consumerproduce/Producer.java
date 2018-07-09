package com.hello.design.consumerproduce;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {

	private Queue<String> queue;
	
	private AtomicInteger atomicInteger;

	public Producer(Queue<String> queue,AtomicInteger atomicInteger) {
		this.atomicInteger = atomicInteger;
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
			int i= atomicInteger.getAndIncrement();
			System.out.println("生产: " + i );
			queue.offer(i + "");
		}

	}

}
