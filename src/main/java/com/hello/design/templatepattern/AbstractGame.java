package com.hello.design.templatepattern;

public abstract class AbstractGame {

	protected abstract void start();

	protected abstract void run();

	protected abstract void end();

	public final void play() {
		start();
		run();
		end();
	}
	
}
