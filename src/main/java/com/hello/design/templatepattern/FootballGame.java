package com.hello.design.templatepattern;

public class FootballGame extends AbstractGame {

	@Override
	protected void start() {
		System.out.println("start football game...");
	}

	@Override
	protected void run() {
		System.out.println("run football game...");
	}

	@Override
	protected void end() {
		System.out.println("end football game...");
	}

}
