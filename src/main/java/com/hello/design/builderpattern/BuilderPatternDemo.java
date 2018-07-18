package com.hello.design.builderpattern;

public class BuilderPatternDemo {
	public static void main(String[] args) {
		MealBuilder builder = new MealBuilder();
		Meal meal = builder.produceMeal();
		System.out.println(meal);
	}
}
