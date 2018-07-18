package com.hello.design.builderpattern;

public class Meal {

	private Drink drink;

	private Food food;

	public Drink getDrink() {
		return drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	@Override
	public String toString() {
		return "Meal [drink=" + drink + ", food=" + food + "]";
	}
	
}
