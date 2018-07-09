package com.hello.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hello.dao.TestDAO;
import com.hello.model.Person;

@Service
public class TransactionDemo {
	
	@Autowired
	private TestDAO testDAO;
	
	@Transactional
	public void testTransaction(){
		Person person = new Person();
		person.setName("tom");
		person.setSalary("20000");
		person.setMgr("jack");
		testDAO.add(person);
		throw new RuntimeException("exception");
	}
	
}
