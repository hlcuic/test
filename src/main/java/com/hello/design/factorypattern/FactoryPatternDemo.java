package com.hello.design.factorypattern;

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
