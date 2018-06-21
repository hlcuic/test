package com.hello.design.orderpattern;

public class LightUpCommand implements Command{
	
	Light light;
	
	LightUpCommand(Light light){
		this.light = light;
	}
	
	@Override
	public void execute() {
		light.on();
	}

}
