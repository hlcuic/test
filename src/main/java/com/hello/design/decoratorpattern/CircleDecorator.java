package com.hello.design.decoratorpattern;

public class CircleDecorator extends ShapeDecorator {

	public CircleDecorator(Shape decoratorShape) {
		super(decoratorShape);
	}
	
	@Override
	public void draw() {
		decoratorShape.draw();
		setColor(decoratorShape);
	}

	public void setColor(Shape decoratorShape){
		System.out.println("decorator red Color...");
	}

}
