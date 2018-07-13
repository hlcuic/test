package com.hello.design.abstractfactorypattern;

public class ShapeFactory implements AbstractFactory {

	public Shape getShape(String type) {

		if (type == null) {
			return null;
		}

		if (type.equalsIgnoreCase("circle")) {
			return new Circle();
		} else if (type.equalsIgnoreCase("rectangle")) {
			return new Rectangle();
		} else if (type.equalsIgnoreCase("square")) {
			return new Square();
		}

		return null;
	}

	@Override
	public Color getColor(String colorType) {
		return null;
	}
}
