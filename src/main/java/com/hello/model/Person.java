
package com.hello.model;

import com.hello.annotation.NotNull;

public class Person extends Animal{

	@NotNull(value = "编号不能为空!")
	private String id;

	@NotNull(value = "姓名不能为空!")
	private String name;

	private String salary;

	private String mgr;
	
	public Person(){}
	
	public Person(String id, String name, String salary, String mgr) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.mgr = mgr;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getMgr() {
		return mgr;
	}

	public void setMgr(String mgr) {
		this.mgr = mgr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", salary=" + salary + ", mgr=" + mgr + "]";
	}

}
