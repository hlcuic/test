package com.hello.design.orderpattern;

public class SimpleControl {
	
	Command command;
	
	public void setCommand(Command command){
		this.command = command;
	}
	
	public void pressButton(){
		command.execute();
	}
}
