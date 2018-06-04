package com.hello.source.jdk;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class MapDemo {
	public static void main(String[] args) {
		testHashMap();
		testHashTable();
		testConcurrenthashMap();
		testTreeMap();
		testLinkedhashmap();
	}
	
	/**
	 * 1:hashmap 内部维护Node[] 数组 ,根据key.hash计算hash值，决定该value存储位置
	 * 2:线程不安全,方法没有加锁
	 * 3:默认hashmap存储容量16，转换因子0.75，容量必须2的次方数，当数量超过容量时，都会扩容resize
	 */
	private static void testHashMap(){
		Map<String,String> hashmap = new HashMap<>();
		//key 可以为 null
		hashmap.put(null, "value");
		//value 可以为null
		hashmap.put("key", null);
	}
	
	/**
	 * 1:线程安全，方法上加锁synchronized
	 * 2:key和value不能为空
	 * 3:内部维护Entry[]（hash,key,value,index）
	 */
	private static void testHashTable(){
		Map<String,String> hashtable = new Hashtable<>();
		hashtable.put("key", "value");
	}
	
	/**
	 * 1：key和value不能为空
	 * 2:在片段上加锁，锁粒度变小
	 */
	private static void testConcurrenthashMap(){
		Map<String,String> conconcurrenthashmap = new ConcurrentHashMap<>();
		conconcurrenthashmap.put("key", "value");
	}
	
	/**
	 * 1:有序，稳定
	 * 2:线程不安全
	 */
	private static void testTreeMap(){
		Map<String,String> treemap = new TreeMap<>();
		treemap.put("key", "value");
	}
	
	private static void testLinkedhashmap(){
		Map<String,String> linkedhashmap = new LinkedHashMap<>();
		linkedhashmap.put("1", "value");
		linkedhashmap.put("3", "value");
		linkedhashmap.put("2", "value");
		System.out.println(linkedhashmap);
	}
	
	
}
