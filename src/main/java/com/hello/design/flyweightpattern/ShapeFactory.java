package com.hello.design.flyweightpattern;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ShapeFactory {

	private static Map<String, Shape> registerMap = new HashMap<>();

	public static Shape getCircle(String color) {
		Shape shape = registerMap.get(color);
		if (shape == null) {
			 shape = new Circle(getRandomNum(),getRandomNum(),color);
			 registerMap.put(color, shape);
		}
		return shape;
	}
	
	public static Integer getRandomNum(){
		return new Random().nextInt(100);
	}
	
}
