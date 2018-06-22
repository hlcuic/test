package com.hello.design.orderpattern;

public class SimpleControlTest {
	public static void main(String[] args) {
		SimpleControl control = new SimpleControl();
		Light light = new Light();
		ICommand commandup = new LightUpCommand(light);
		control.setCommand(commandup);
		control.pressButton();
		ICommand commanddown = new LightDownCommand(light);
		control.setCommand(commanddown);
		control.pressButton();
	}
}
