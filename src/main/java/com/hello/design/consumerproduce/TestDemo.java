package com.hello.design.consumerproduce;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class TestDemo {
	
	public static void main(String[] args) {
		BlockingQueue queue = new LinkedBlockingQueue(10);
		Producer p1 = new Producer(queue);
		Producer p2 = new Producer(queue);
		Producer p3 = new Producer(queue);
		Consumer c1 = new Consumer(queue);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(p1);
		exec.execute(p2);
		exec.execute(p3);
		exec.execute(c1);
		exec.shutdown();
	}
	
}
