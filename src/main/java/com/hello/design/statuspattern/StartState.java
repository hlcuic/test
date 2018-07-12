package com.hello.design.statuspattern;

public class StartState implements State {

	@Override
	public void doAction(Context context) {
		context.setState(this);
	}
	
	public String toString(){
		return "启动状态...";
	}

}
