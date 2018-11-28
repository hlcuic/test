package com.hello.threads;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {
	public static void main(String[] args) {
		ConcurrentHashMap map = new ConcurrentHashMap();
		HashMap map1 = new HashMap();
    	map.get("1");
    	System.out.println(Integer.parseInt("0001111", 2) );
    	System.out.println(Integer.parseInt("0011111", 2)  );
    	System.out.println(Integer.parseInt("0111111", 2) );
    	System.out.println(Integer.parseInt("1111111", 2)  );
    	System.out.println("------------------");
    	System.out.println(Integer.parseInt("0001111", 2) & 15);
    	System.out.println(Integer.parseInt("0011111", 2) & 15);
    	System.out.println(Integer.parseInt("0111111", 2) & 15);
    	System.out.println(Integer.parseInt("1111111", 2) & 15);
//    	Hashtable<K, V>.class
	}
}
