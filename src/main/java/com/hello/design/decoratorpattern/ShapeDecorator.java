package com.hello.design.decoratorpattern;

/**
 * 装饰器类
 * @author Administrator
 *
 */
public abstract class ShapeDecorator implements Shape{
	
	protected Shape decoratorShape;
	
	public ShapeDecorator(Shape decoratorShape){
		this.decoratorShape = decoratorShape;
	}

	@Override
	public void draw() {
		decoratorShape.draw();
	}
	
}
