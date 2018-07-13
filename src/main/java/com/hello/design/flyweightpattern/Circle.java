package com.hello.design.flyweightpattern;

public class Circle implements Shape {
	
	private Integer x;
	
	private Integer y;
	
	private String color;
	
	public Circle(Integer x, Integer y, String color) {
		super();
		this.x = x;
		this.y = y;
		this.color = color;
	}

	@Override
	public void draw() {
		System.out.println(this);
	}
	
	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Circle [x=" + x + ", y=" + y + ", color=" + color + "]";
	}

}
