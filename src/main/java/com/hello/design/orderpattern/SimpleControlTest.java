package com.hello.design.orderpattern;

public class SimpleControlTest {
	public static void main(String[] args) {
		SimpleControl control = new SimpleControl();
		Light light = new Light();
		Command command = new LightUpCommand(light);
		control.setCommand(command);
		control.pressButton();
	}
}
