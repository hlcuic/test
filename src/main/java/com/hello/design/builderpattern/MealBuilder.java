package com.hello.design.builderpattern;

public class MealBuilder {
	
	public Meal produceMeal(){
		Meal meal = new Meal();
		meal.setDrink(new RedTea());
		meal.setFood(new Rice());
		return meal;
	}
	
}
