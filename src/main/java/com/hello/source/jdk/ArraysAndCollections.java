package com.hello.source.jdk;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArraysAndCollections {
	public static void main(String[] args) {
//		arraysSort();
		collectionsSort();
	}
	
	private static void collectionsSort(){
		List<Integer> arr = Arrays.asList(1,4,2,5,34);
		System.out.println(arr);
		Collections.sort(arr);
		System.out.println(arr);
	}
	
	private static void arraysSort(){
		int[] arr = {1,4,2,5,34};
		System.out.println(Arrays.toString(arr));
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
}
