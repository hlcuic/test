package com.hello.design.orderpattern;

public class LightDownCommand implements ICommand{
	
	Light light;
	
	LightDownCommand(Light light){
		this.light = light;
	}

	@Override
	public void execute() {
		light.down();
	}

}
