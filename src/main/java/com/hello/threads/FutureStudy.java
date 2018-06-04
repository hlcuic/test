package com.hello.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.util.CollectionUtils;

public class FutureStudy {

	public static void main(String[] args) throws Exception {
		/**
		 * 这里的线程数定义是5个，就是核心线程数，如果我有10个任务，那么5个线程分别处理前5个任务，其他5个任务放到队列中,
		 * 所以查询结果前5个先打印出来，然后隔了几毫秒有打印后面5个
		 */
		ExecutorService executors = Executors.newFixedThreadPool(5);
		List<MyCallable> list = wrapTask();
		List<Future<Integer>> futureList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(list)) {
			list.forEach(task -> {
				Future<Integer> future = executors.submit(task);
				futureList.add(future);
			});
		}
		if (!CollectionUtils.isEmpty(futureList)) {
			for (Future<Integer> future : futureList) {
				//线程阻塞，如果前一个线程的数据没有查询结束，那么后面的遍历将阻塞
				Integer i = future.get();
				System.out.println(i);
			}
		}
		executors.shutdown();
	}

	private static List<MyCallable> wrapTask() {
		List<MyCallable> resultList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			resultList.add(new MyCallable(i));
		}
		return resultList;
	}
}

class MyCallable implements Callable<Integer> {

	private Integer i;

	MyCallable(Integer i) {
		this.i = i;
	}

	@Override
	public Integer call() throws Exception {
		Thread.sleep(1000);
		return i;
	}

}
