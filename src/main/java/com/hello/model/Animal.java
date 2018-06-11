package com.hello.model;

import com.hello.annotation.NotNull;

public class Animal {
	
	@NotNull(value = "身高不能为空!")
	private String heigh;
	
	private String weight;

	public String getHeigh() {
		return heigh;
	}

	public void setHeigh(String heigh) {
		this.heigh = heigh;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

}
