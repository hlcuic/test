package com.hello.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.util.CollectionUtils;

public class ExecutorsStudy {
	
	public static void main(String[] args) {
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

