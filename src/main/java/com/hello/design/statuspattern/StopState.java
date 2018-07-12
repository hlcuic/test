package com.hello.design.statuspattern;

public class StopState implements State {

	@Override
	public void doAction(Context context) {
		context.setState(this);
	}
	
	public String toString(){
		return "停止状态...";
	}

}
