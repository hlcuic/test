package com.hello.model;

import java.util.List;

public class Car {
	
	private Integer id;
	
	private String desc;
	
	private String name;
	
	private List<String> list;
	
	public Car(){}
	
	public Car(Integer id, String desc, String name, List<String> list) {
		this.id = id;
		this.desc = desc;
		this.name = name;
		this.list = list;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", desc=" + desc + ", name=" + name + ", list=" + list + "]";
	}
	
}
