package com.hello.threads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解决方式3：使用automInteger
 * 乐观锁机制：认为线程安全问题不会发生，读取内存中数据，操作后，新值与内存值。
 * 悲观锁机制：认为线程安全问题会发生，所以提前采取预防措施
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
						System.out.println(demo.num+"  "+Thread.currentThread().getName());
					}
				}

			},"线程"+i).start();
		}

		while (Thread.activeCount() > 1) {
			Thread.yield();
		}

		System.out.println(demo.num);

	}
}
