package com.hello.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class AlgoCase {
	public static void main(String[] args) {
		question1();
		question2();
		question3();
		question4();
		getFactors();
		getFactors(1);
		test1();
	}

	public static void question4() {
		int[] arr = { 50, 2, 51, 1, 9, 3, 23 };
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(arr));
	}

	public static void question3() {
		int a = 0, b = 1;
		for (int i = 0; i < 20; i++) {
			if (i == 0) {
				System.out.print(a + "\t");
				continue;
			}
			if (i == 1) {
				System.out.print(b + "\t");
				continue;
			}
			int c = a + b;
			a = b;
			b = c;
			System.out.print(c + "\t");
			if (i % 5 == 0) {
				System.out.println();
			}
		}
	}

	public static void question2() {
		String[] arr1 = { "1", "2" };
		String[] arr2 = { "A", "B", "C", "D" };
		String[] arr3 = new String[arr1.length + arr2.length];
		int size = arr1.length > arr2.length ? arr1.length : arr2.length;
		for (int i = 0, j = 0; i < size; i++) {
			if (i < arr1.length) {
				arr3[j++] = arr1[i];
			}
			if (i < arr2.length) {
				arr3[j++] = arr2[i];
			}
		}
		System.out.println(Arrays.toString(arr3));
	}

	public static void question1() {
		int[] arr = { 1, 2, 3, 4 };
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		System.out.println(sum);
		System.out.println("sum=" + sum(1));
	}

	public static int sum(int n) {
		if (n == 4) {
			return n;
		}
		return n + sum(n + 1);
	}

	private static void getFactors() {
		int count = 1;
		for (int i = 2; i <= 100; i++) {
			boolean flag = true;
			for (int j = 2; j <= 50; j++) {
				if (i % j == 0 && i != j) {
					flag = false;
				}
			}
			if (flag) {
				System.out.print(i + "\t");
				if (count % 5 == 0) {
					System.out.println();
				}
				count++;
			}
		}
	}

	private static int[] getFactors(int input) {
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 2; i <= input; i++) {
			if (input % i == 0) {
				result.add(i);
				input = input / i;
				i = 1;
			}
		}
		return toArray(result);
	}

	private static int[] toArray(List<Integer> list) {
		int[] arr = new int[list.size()];
		for (int index = 0; index < list.size(); index++) {
			arr[index] = list.get(index).intValue();
		}
		return arr;
	}

	public static List<Integer> getNums(int input) {
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 2; i <= input; i++) {
			if (input % i == 0) {
				result.add(i);
				System.out.println("i" + i);
				result.addAll(getNums(input / i));
				break;
			}
		}
		return result;
	}


	private static void test1() {
		Runnable r1 = new MyRun("thread1");
		Runnable r2 = new MyRun("thread2");
		new Thread(r1).start();
		new Thread(r2).start();
	}
	
}

class MyRun implements Runnable {

	private static AtomicBoolean exist = new AtomicBoolean(false);

	private String name;

	MyRun(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		if (exist.compareAndSet(false, true)) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			doOperate();
			exist.set(false);
		}
	}

	private void doOperate() {
		System.out.println(name + " entering...");
		try {
			System.out.println(name + " working...");
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name + " leaving");
	}

}
