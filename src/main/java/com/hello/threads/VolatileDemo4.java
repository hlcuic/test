package com.hello.threads;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决方式3：使用automInteger
 * 
 * @author Administrator
 *
 */
public class VolatileDemo4 {

	private AtomicInteger num = new AtomicInteger();

	private void inc() {
		num.getAndIncrement();
	}

	public static void main(String[] args) {

		final VolatileDemo4 demo = new VolatileDemo4();

		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int i = 0; i < 1000; i++) {
						demo.inc();
					}
				}

			}).start();
		}

		while (Thread.activeCount() > 1) {
			Thread.yield();
		}

		System.out.println(demo.num);

	}
}
