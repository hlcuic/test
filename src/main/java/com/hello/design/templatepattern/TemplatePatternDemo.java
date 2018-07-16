package com.hello.design.templatepattern;

public class TemplatePatternDemo {
	public static void main(String[] args) {
		AbstractGame basketGame = new BasketBallGame();
		basketGame.play();
		System.out.println(".................................");
		AbstractGame footGame = new FootballGame();
		footGame.play();
	}
}
