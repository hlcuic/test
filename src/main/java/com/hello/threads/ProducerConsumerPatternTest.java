package com.hello.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerPatternTest {
	public static void main(String[] args) {
		//synchronized notifyAll wait
//		test1();
		
		//lock condition signAll await
//		test2();
		
		//BlockingQueue
		test3();
	}
	
	private static void test3(){
		Resource resource = new Resource(10);
		Thread p1 = new Thread(new Producer(resource),"生产者1");
		Thread p2 = new Thread(new Producer(resource),"生产者2");
		Thread p3 = new Thread(new Producer(resource),"生产者3");
		
		Thread c1 = new Thread(new Consumer(resource),"消费者1");
//		Thread c2 = new Thread(new Consumer(resource),"消费者2");
//		Thread c3 = new Thread(new Consumer(resource),"消费者3");
		p1.start();
		p2.start();
		p3.start();
		
		c1.start();
	}
	
	private static void test2(){
		Lock lock = new ReentrantLock();
		Condition productCondition = lock.newCondition(); 
		Condition consumeCondition = lock.newCondition();
		Resource resource = new Resource(10,productCondition,consumeCondition,lock);
		Thread p1 = new Thread(new Producer(resource),"生产者1");
		Thread p2 = new Thread(new Producer(resource),"生产者2");
		Thread p3 = new Thread(new Producer(resource),"生产者3");
		
		Thread c1 = new Thread(new Consumer(resource),"消费者1");
		p1.start();
		p2.start();
		p3.start();
		
		c1.start();
	}
	
	private static void test1(){
		Resource resource = new Resource(10);
		Thread p1 = new Thread(new Producer(resource),"生产者1");
		Thread p2 = new Thread(new Producer(resource),"生产者2");
		Thread p3 = new Thread(new Producer(resource),"生产者3");
		
		Thread c1 = new Thread(new Consumer(resource),"消费者1");
//		Thread c2 = new Thread(new Consumer(resource),"消费者2");
//		Thread c3 = new Thread(new Consumer(resource),"消费者3");
		p1.start();
		p2.start();
		p3.start();
		
		c1.start();
//		c2.start();
//		c3.start();
	}
}

/**
 * 生产者
 * @author Administrator
 */
class Producer implements Runnable{
	
	private Resource resource;
	
	Producer(Resource resource){
		this.resource = resource;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			resource.add();
//			resource.put();
			resource.set();
		}
	}
}

class Consumer implements Runnable{
	
	private Resource resource;
	
	Consumer(Resource resource){
		this.resource = resource;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			resource.take();
//			resource.poll();
			resource.get();
		}
		
	}
	
}

/**
 * 缓存区
 * @author Administrator
 *
 */
class Resource{
	private int size; //容量
	private int currentCount;//当前数量
	
	private Condition productCondition;
	private Condition consumeCondition;
	private Lock lock;
	
	private BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(10);
	
	Resource(){
		
	}
	
	Resource(int size){
		this.size = size;
	}
	
	Resource(int size,Condition productCondition,Condition consumeCondition,Lock lock){
		this.size = size;
		this.productCondition = productCondition;
		this.consumeCondition = consumeCondition;
		this.lock = lock;
	}
	
	public void set(){
		blockingQueue.add(1);
		System.out.println(Thread.currentThread().getName() +
					"放入一个对象,当前对象数量"+blockingQueue.size());
	}
	
	public void get(){
		try {
			blockingQueue.take();
			System.out.println(Thread.currentThread().getName() +
					"取走一个对象,当前对象数量"+blockingQueue.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void put(){
		try {
			lock.lock();
			if(currentCount<size){
				currentCount++;
				System.out.println(Thread.currentThread().getName() +
						"放入一个对象,当前对象数量"+currentCount);
				consumeCondition.signalAll(); //通知所有消费者
			}else{
				//如果物品已满，则生产者阻塞
				try {
					productCondition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} finally {
			lock.unlock();
		}
	}
	
	public void poll(){
		try {
			lock.lock();
			if(currentCount>0){
				currentCount--;
				System.out.println(Thread.currentThread().getName() +
						"取走一个对象,当前对象数量"+currentCount);
				productCondition.signalAll(); //通知等待的生产者
			}else{
				//容器已空，消费者阻塞
				try {
					consumeCondition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} finally {
			lock.unlock();
		}
		
	}
	
	public synchronized void add(){
		if(currentCount<size){
			currentCount++;
			System.out.println(Thread.currentThread().getName() +
					"放入一个对象,当前对象数量"+currentCount);
			notifyAll(); //通知等待的消费者
		}else{
			//如果物品已满，则生产者阻塞
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void take(){
		if(currentCount>0){
			currentCount--;
			System.out.println(Thread.currentThread().getName() +
					"取走一个对象,当前对象数量"+currentCount);
			notifyAll(); //通知等待的生产者
		}else{
			//容器已空，消费者阻塞
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}
	
}
