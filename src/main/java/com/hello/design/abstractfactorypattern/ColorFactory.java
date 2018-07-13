package com.hello.design.abstractfactorypattern;

public class ColorFactory implements AbstractFactory {

	@Override
	public Shape getShape(String shapeType) {
		return null;
	}

	@Override
	public Color getColor(String colorType) {
		
		if(null==colorType){
			return null;
		}
		
		if(colorType.equalsIgnoreCase("green")){
			return new Green();
		}else if(colorType.equalsIgnoreCase("red")){
			return new Red();
		}else if(colorType.equalsIgnoreCase("blue")){
			return new Blue();
		}
		
		return null;
	}

}
