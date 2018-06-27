package com.hello.design.orderpattern;

public class LightUpCommand implements ICommand{
	
	Light light;
	
	LightUpCommand(Light light){
		this.light = light;
	}
	
	@Override
	public void execute() {
		light.on();
	}

	@Override
	public void undo() {
		light.down();
	}
	
	

}
