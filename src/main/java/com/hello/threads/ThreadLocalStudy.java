package com.hello.threads;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalStudy {

	private ThreadLocal<Map<String, String>> threadlocal = new ThreadLocal<Map<String, String>>() {
		protected Map<String, String> initialValue() {
			return new HashMap<>();
		}
	};

	public static void main(String[] args) {
		Map<Long,Thread> threadMap = new HashMap<>();
		ThreadLocalStudy study = new ThreadLocalStudy();
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				Map<String, String> map = study.threadlocal.get();
				map.put("1", "tom");
			}
			
		},"线程1");
		t1.start();
		threadMap.put(t1.getId(),t1);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				Map<String, String> map = study.threadlocal.get();
				String str = map.get("1");
				System.out.println(str);
			}
			
		},"线程2");
		t2.start();
		
		threadMap.put(t2.getId(),t2);
		
		threadMap.forEach((id,thread) ->{
			
		});
	}
}
