package com.hello.threads;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

import com.hello.model.Person;

public class CASTest {
	
	private static interface A{
		void add(String id);
	}
	
	private static AtomicInteger atomicInteger = new AtomicInteger(1);

	public static void main(String[] args) {
		// test1();
//		Class clazz = AbstractQueuedSynchronizer.class;
//		test2();
		test11(id->{
			System.out.println(id);
		});
	}
	
	public static void test11(A a){
		a.add("1");
	}

	private static void test2() {
		int i = atomicInteger.incrementAndGet();
		System.out.println(i);
	}

	private static void test1() {
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + ":::" + atomicInteger.getAndIncrement());
				}

			}).start();
		}
	}

}
