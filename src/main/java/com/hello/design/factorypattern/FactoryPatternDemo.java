package com.hello.design.factorypattern;
/**
 * 简单工程模式，将创建对象交给对象工厂
 * @author Administrator
 *
 */
public class FactoryPatternDemo {
	public static void main(String[] args) {
		ShapeFactory shapeFactory = new ShapeFactory();
		Shape shape = shapeFactory.getShape("circle");
		shape.draw();
		shape = shapeFactory.getShape("rectangle");
		shape.draw();
		shape = shapeFactory.getShape("square");
		shape.draw();
	}
}
