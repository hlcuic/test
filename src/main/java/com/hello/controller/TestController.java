package com.hello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hello.dao.TestDAO;
import com.hello.model.Person;

@RestController
public class TestController {

	@Autowired
	private TestDAO dao;

	@GetMapping("/helloworld")
	public String helloworld() {
		List<Person> persons = dao.listPersons();
		StringBuilder sb = new StringBuilder();
		persons.forEach(p -> {
			sb.append(p.getName());
		});
		return sb.toString();
	}

}