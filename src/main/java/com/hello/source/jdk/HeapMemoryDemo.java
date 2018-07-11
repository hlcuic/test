package com.hello.source.jdk;

import java.util.ArrayList;
import java.util.List;

import com.hello.model.Person;

/**
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * 内存堆溢出
 * @author Administrator
 *
 */
public class HeapMemoryDemo {
	public static void main(String[] args) {
		List<Person> personList = new ArrayList<>();
		int i = 0;
		while (true) {
			Person person = new Person();
			personList.add(person);
			System.out.println(++i);
		}

	}
}
