package com.hello.design.decoratorpattern;

public class DecoratorPatternDemo {
	public static void main(String[] args) {
		Shape shape = new Circle();
		Shape shapeDecorator = new CircleDecorator(shape);
		shape.draw();
		System.out.println("........................");
		shapeDecorator.draw();
	}
}
