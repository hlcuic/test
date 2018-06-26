package com.hello.source.jdk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeSet;

public class ListDemo {
	public static void main(String[] args) {
//		testArrayList();
//		testLinkedList();
//		testHashSet();
//		testTreeSet();
//		testQueue();
		testStack();
	}
	
	/**
	 * 队列，先进先出
	 */
	private static void testQueue(){
		Queue<String>  queue = new LinkedList<String>();
		queue.add("1");
		queue.add("2");
		queue.add("3");
		queue.add("4");
		queue.add("5");
		int j = queue.size();
		for(int i=0;i<j;i++){
			System.out.print(queue.poll());
		}
	}
	
	/**
	 * 栈结构，先进后出
	 */
	private static void testStack(){
		Stack<String> stack = new Stack<String>();
		stack.add("aaa");
		stack.add("bbb");
		stack.add("ccc");
		stack.add("ddd");
		stack.add("fff");
		int length = stack.size();
		for(int i=0;i<length;i++){
			String s = stack.pop();
			System.out.println(s);
		}
		
	}

	/**
	 * 底层维护数组，检索效率比较高，添加会追加到数组的最后,如果指定位置添加元素，那么效率
	 * 很低，因为从添加位置开始，所有的元素都会向后移动
	 *  transient Object[] elementData; // non-private to simplify nested class access
	 */
	private static void testArrayList() {
		ArrayList<String> list = new ArrayList<>();
		//追加到数组末尾
		list.add("1");
		//指定位置开始，后面的元素都向后移动
		list.add(1,"2");
		//根据索引检索元素
		list.get(0);
		list.subList(0, 1);
	}

	/**
	 * 维护一个双向链表Node,记录next，prev以及元素地址
	 * 稳定性，输入顺序和输出一致
	 */
	private static void testLinkedList() {
		LinkedList<String> list = new LinkedList<>();
		//追加到list后面，如果没有元素就是第一个，如果有，就放到最后一个元素后面
		list.add("1");
		//将元素添加到链表的第一个位置
		list.addFirst("1");
		//添加到list最后一个,等价add()
		list.addLast("2");
		//返回指定位置的元素，检索时判断在size的前半部分（从前往后）还是后半部分（从后往前）,检索效率比较低
		list.get(0);
		list.getFirst();
		list.getLast();
	}

	/**
	 * 底层维护了hashmap，hashset只是维护了hashmap的一个快照
	 * 保证有序性，key值必须重写hashcode和equals,去重就是比较key值
	 * 的equals
	 */
	private static void testHashSet() {
		HashSet<String> set = new HashSet<>();
		//调用map的put方法
		set.add("1");
		set.addAll(new ArrayList<>());
	}

	/**
	 * 内部维护NavigableMap,实现了treemap(内置了比较器，所以有序)
	 */
	private static void testTreeSet() {
		TreeSet<String> treeSet = new TreeSet();
		treeSet.add("1");
	}
	
}
