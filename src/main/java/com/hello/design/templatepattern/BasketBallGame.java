package com.hello.design.templatepattern;

public class BasketBallGame extends AbstractGame {

	@Override
	protected void start() {
		System.out.println("start basketball game...");
	}

	@Override
	protected void run() {
		System.out.println("run basketball game...");
	}

	@Override
	protected void end() {
		System.out.println("end basketball game...");
	}

}
