package com.hello.design.consumerproduce;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class TestDemo {

	public static void main(String[] args) {
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);
		AtomicInteger atomicInteger = new AtomicInteger();
		Producer p1 = new Producer(queue, atomicInteger);
		Producer p2 = new Producer(queue, atomicInteger);
		Producer p3 = new Producer(queue, atomicInteger);
		Consumer c1 = new Consumer(queue);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(p1);
		exec.execute(p2);
		exec.execute(p3);
		exec.execute(c1);
		exec.shutdown();
	}
}
