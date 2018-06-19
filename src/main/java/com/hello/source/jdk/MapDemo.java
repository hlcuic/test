package com.hello.source.jdk;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.io.Resources;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.hello.model.DetailKey;

public class MapDemo {
	public static void main(String[] args) throws IOException {
//		testHashMap();
//		testTreeMap();
//		testHashTable();
//		testConcurrenthashMap();
//		testLinkedhashmap();
//		testhashCodeAndEquals2();
	}
	
	/**
	 * 如果以自定义对象为key,需要重写hashcode和equals，两个对象equals相同那么hashcode一定
	 * 相同，hashcode相同，equals不一定相等。
	 */
	public static void testHashCodeAndEquals1(){
		DetailKey key1 = new DetailKey("1");
		DetailKey key2 = new DetailKey("1");
		System.out.println(key1.hashCode());
		System.out.println(key2.hashCode());
	}
	
	public static void testhashCodeAndEquals2(){
		String s1 = "hello hello";
		String[] sarr = s1.split(" ");
		System.out.println(s1.hashCode());
		System.out.println(sarr[0].hashCode());
		System.out.println(sarr[1].hashCode());
	}
	
	/**
	 * 测试Properties类
	 * @throws IOException
	 */
	public static void testProperties() throws IOException{
		Properties properties = new Properties();
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource resource = resolver.getResource("classpath:application.properties");
		properties.load(resource.getInputStream());
		properties.forEach((key,value)->{
			System.out.println(key+"="+value);
		});
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
		TreeMap<String,String> treemap = new TreeMap<>();
		treemap.put("key", "value");
		treemap.firstKey();
		treemap.lastKey();
	}
	
	private static void testLinkedhashmap(){
		Map<String,String> linkedhashmap = new LinkedHashMap<>();
		linkedhashmap.put("1", "value");
		linkedhashmap.put("3", "value");
		linkedhashmap.put("2", "value");
		System.out.println(linkedhashmap);
	}
	
	
}
