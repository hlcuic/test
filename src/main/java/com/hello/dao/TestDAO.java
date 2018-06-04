package com.hello.dao;

import java.util.List;

import com.hello.model.Person;

public interface TestDAO {
	List<Person> listPersons();
	int add(Person person);
	int update(Person person);
	int delete(int id);
}
