package com.hello.design.abstractfactorypattern;

public interface AbstractFactory {
	Shape getShape(String shapeType);
	Color getColor(String colorType);
}
