package com.hello.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import java.util.concurrent.*;

public class CreateThreadPoolExecutorsDemo {
	public static void main(String[] args) {
//		Class threadPoolExecutorClass = ThreadPoolExecutor.class;
//		BlockingQueue.class
//		ArrayBlockingQueue.class
//		SynchronousQueue.class
		// 创建一个线程池，核心数5，最大数10,200毫秒没有执行任务会被回收，对列是数组有界对列
		// 来一个请求，先交给核心线程处理，线程池被创建时没有线程，来一个请求创建一个线程，当线程数超过核心线程数时，将请求
		// 放到对列中,当对列容量已满时，再创建线程处理新来的请求，如果线程数超过核心线程数，则报错

		ThreadPoolExecutor executor = null;
		String type = "limited";
		// 有界
		if ("limited".equals(type)) {
			executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5),
					new RejectedExecutionHandler() {

						@Override
						public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
							throw new RuntimeException("服务器忙，请稍后重试!");
						}
					});

		}
		// 无界
		else {
			executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		}

		for (int i = 0; i < 20; i++) {
			MyTask myTask = new MyTask(i);
			try {
				executor.execute(myTask);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" + executor.getQueue().size()
					+ "，已执行玩别的任务数目：" + executor.getCompletedTaskCount());
		}
		
		//关闭线程池后没有真正关闭，而是等对列中所有任务执行完毕后关闭
		executor.shutdown();
		//不等任务完成，直接关闭线程池,终止正在执行的任务
//		executor.shutdownNow();
	}
}

class MyTask implements Runnable {
	private int taskNum;

	public MyTask(int num) {
		this.taskNum = num;
	}

	@Override
	public void run() {
		System.out.println("正在执行task " + taskNum);
		try {
			Thread.currentThread().sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("task " + taskNum + "执行完毕");
	}
}
