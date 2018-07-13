package com.hello.design.abstractfactorypattern;

public class FactoryProducer {
	public AbstractFactory getAbstractFactory(String factoryType) {
		if (null == factoryType) {
			return null;
		}
		if (factoryType.equalsIgnoreCase("color")) {
			return new ColorFactory();
		} else if (factoryType.equalsIgnoreCase("shape")) {
			return new ShapeFactory();
		}

		return null;
	}
}
