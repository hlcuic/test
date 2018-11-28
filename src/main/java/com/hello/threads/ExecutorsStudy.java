package com.hello.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.util.CollectionUtils;

public class ExecutorsStudy {
	
	public static void main(String[] args) throws Exception {
//		test1();
//		test2();
//		test3();
		test4();
	}
	
	private static void test4(){
		SynchronousQueue<String> queue = new SynchronousQueue<String>();
//		new Thread(new Runnable(){
//
//			@Override
//			public void run() {
//				try {
//					System.out.println("start put element");
//					queue.put("1");
//					System.out.println("end put element");
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			
//		}).start();
		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					System.out.println("start take element");
					System.out.println(queue.take());
					System.out.println("end take element");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}).start();
		
	}
	
	public static void test3(){
		Executors.newCachedThreadPool();
		Executors.newSingleThreadExecutor();
		Executors.newFixedThreadPool(5);
		ThreadPoolExecutor executors = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());;
		for(int i=0;i<10000;i++){
			executors.execute(new Runnable(){

				@Override
				public void run() {
					System.out.println("线程名称："+Thread.currentThread().getName()
							+"核心线程："+executors.getCorePoolSize()+"最大线程数："
							+executors.getMaximumPoolSize()+"队列大小："+executors.getQueue().size());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			});
		}
		executors.shutdown();
	}
	
	private static void test2() throws Exception{
		ExecutorService executors = Executors.newFixedThreadPool(7);
		List<FutureTask> resultList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			FutureTask<MyCall> task = new FutureTask(new MyCall(i));
			resultList.add(task);
		}
		if (!CollectionUtils.isEmpty(resultList)) {
			resultList.forEach(task -> {
				executors.execute(task);
			});
		}
		
		for(FutureTask task:resultList){
			Integer i = (Integer) task.get();
			System.out.println(i);
		}
		
		executors.shutdown();
	}
	
	private static void test1(){
		ExecutorService executors = Executors.newFixedThreadPool(7);
		List<MyRunnable> list = wrapTask();
		if (!CollectionUtils.isEmpty(list)) {
			list.forEach(task -> {
				executors.execute(task);
			});
		}
		executors.shutdown();
	}
	
	private static List<MyRunnable> wrapTask() {
		List<MyRunnable> resultList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			resultList.add(new MyRunnable(i));
		}
		return resultList;
	}
	
}

class MyRunnable implements Runnable {

	private Integer i;

	MyRunnable(Integer i) {
		this.i = i;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(i);
	}
}

class MyCall implements  Callable<Integer>{
	
	private Integer i;
	
	MyCall(Integer i){
		this.i = i;
	}
	
	@Override
	public Integer call() throws Exception {
		return i;
	}
	
}
