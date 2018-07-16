package com.hello.design.abstractfactorypattern;

/**
 * 抽象工厂模式：生产工厂的工厂
 * @author Administrator
 *
 */
public class AbstractFactoryDemo {
	public static void main(String[] args) {
		
		FactoryProducer factoryProducer = new FactoryProducer();
		
		AbstractFactory colorFactory = factoryProducer.getAbstractFactory("color");
		Color red = (Color) colorFactory.getColor("red");
		red.fill();
		
		AbstractFactory shapeFactory = factoryProducer.getAbstractFactory("shape");
		Shape circle = (Shape) shapeFactory.getShape("circle");
		circle.draw();
		
	}
	
}
