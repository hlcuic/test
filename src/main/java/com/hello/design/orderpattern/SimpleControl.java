package com.hello.design.orderpattern;

public class SimpleControl {
	
	ICommand command;
	
	public void setCommand(ICommand command){
		this.command = command;
	}
	
	public void pressButton(){
		command.execute();
	}
	
	public void undo(){
		command.undo();
	}
}
