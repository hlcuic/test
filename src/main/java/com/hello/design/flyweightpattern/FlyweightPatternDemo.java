package com.hello.design.flyweightpattern;

/**
 * 享元模式：对于经常用到的对象，维护在池中，例如：字符串池、常量池(-127~128) 避免内存溢出
 * 
 * @author Administrator
 *
 */
public class FlyweightPatternDemo {

	private static String[] colors = { "red", "green", "blue", "yellow", "black" };

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			Shape shape = ShapeFactory.getCircle(getRandomColor());
			shape.draw();
		}
	}

	public static String getRandomColor() {
		return colors[(int) (Math.random() * 5)];
	}
}
