package com.hello.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.logging.log4j.util.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeadLockTest {
	
	private static Object lock1 = new Object();
	
	private static Object lock2 = new Object();
	
	
	public static void main(String[] args) {
//		test1();
//		test2();
		test3();
	}
	
	/**
	 * 当线程池中只有一个核心线程时，在当前正在执行的任务中再次向线程池中丢任务，也会造成死锁，
	 * 因为线程正在执行当前任务，新提交的任务没有线程处理，就会放到对列中，那么新提交任务阻塞，而当前线程等待新提交
	 * 任务返回，也阻塞
	 */
	private static void test3(){
		ExecutorService pool = Executors.newSingleThreadExecutor();
		pool.submit(()->{
			System.out.println(Thread.currentThread().getName()+ " hello");
			try {
				pool.submit(()->{
					return "1";
				}).get();
				System.out.println("dddd");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	/**
	 * 两个线程执行任务，而且获取锁的顺序不同，当第一个线程占用第一把锁，然后执行任务，并且需要占用第二把锁，而第二个
	 * 线程首先占用了第二把锁，然后试图获取第一把锁，结果两个线程都不释放自己占用的锁，都处于阻塞状态
	 */
	private static void test2(){
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				synchronized (lock1) {
					System.out.println("------------task1 get lock1---------");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (lock2) {
						System.out.println("------------task1 get lock2---------");
					}
					System.out.println("------------task1 release lock2---------");
				}
				System.out.println("------------task1 release lock1---------");
			}
			
		}).start();
		
		Thread t2 = new Thread(){
			public void run(){
				synchronized (lock2) {
					System.out.println("------------task2 get lock2---------");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (lock1) {
						System.out.println("------------task2 get lock1---------");
					}
					System.out.println("------------task2 release lock1---------");
				}
				System.out.println("------------task2 release lock2---------");
			}
		};
		t2.start();
	}
	
	private static void test1(){
		try (Forest forest = new Forest()) {
			forest.cutTrees(100, 10, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Forest implements AutoCloseable {
	private static final Logger log = LoggerFactory.getLogger(Forest.class);
	private final ExecutorService pool = Executors.newFixedThreadPool(20);
	private final Logging logging = new Logging();

	void cutTrees(int howManyTrees, int carefulLumberjacks, int yoloLumberjacks)
			throws InterruptedException, TimeoutException {
		CountDownLatch latch = new CountDownLatch(howManyTrees);
		List<Lumberjack> lumberjacks = new ArrayList<>();
		lumberjacks.addAll(generate(carefulLumberjacks, logging::careful));
		lumberjacks.addAll(generate(yoloLumberjacks, logging::yolo));
		IntStream.range(0, howManyTrees).forEach(x -> {
			Lumberjack roundRobinJack = lumberjacks.get(x % lumberjacks.size());
			pool.submit(() -> {
				log.debug("{} cuts down tree, {} left", roundRobinJack, latch.getCount());
				roundRobinJack.cut(latch::countDown);
			});
		});
		if (!latch.await(10, TimeUnit.SECONDS)) {
			throw new TimeoutException("Cutting forest for too long");
		}
		log.debug("Cut all trees");
	}

	private List<Lumberjack> generate(int count, Supplier<Lumberjack> factory) {
		return IntStream.range(0, count).mapToObj(x -> factory.get()).collect(Collectors.toList());
	}

	@Override
	public void close() {
		pool.shutdownNow();
	}

}

class Lumberjack {
	private final String name;
	private final Lock accessoryOne;
	private final Lock accessoryTwo;

	Lumberjack(String name, Lock accessoryOne, Lock accessoryTwo) {
		this.name = name;
		this.accessoryOne = accessoryOne;
		this.accessoryTwo = accessoryTwo;
	}

	void cut(Runnable work) {
		try {
			accessoryOne.lock();
			try {
				accessoryTwo.lock();
				work.run();
			} finally {
				accessoryTwo.unlock();
			}
		} finally {
			accessoryOne.unlock();
		}
	}
}

class Logging {
	private final Lock helmet = new ReentrantLock();
	private final Lock chainsaw = new ReentrantLock();

	Lumberjack careful() {
		return new Lumberjack(Names.getRandomName(), helmet, chainsaw);
	}

	Lumberjack yolo() {
		return new Lumberjack(Names.getRandomName(), chainsaw, helmet);
	}
}

class Names {
	public static String getRandomName() {
		return "jack" + new Random().nextInt();
	}
}