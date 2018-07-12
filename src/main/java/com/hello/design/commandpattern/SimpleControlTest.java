package com.hello.design.commandpattern;

public class SimpleControlTest {
	public static void main(String[] args) {
		SimpleControl control = new SimpleControl();
		Light light = new Light();
		ICommand commandup = new LightUpCommand(light);
		control.setCommand(commandup);
		control.pressButton();
		control.undo();
		ICommand commanddown = new LightDownCommand(light);
		control.setCommand(commanddown);
		control.pressButton();
		control.undo();
	}
}
