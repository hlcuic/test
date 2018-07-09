package com.hello.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hello.dao.TestDAO;
import com.hello.model.Person;

@Service
public class TransDemo {
	
	@Autowired
	private TestDAO testDAO;
	
	@Autowired
	private TransactionDemo tranDemo;
	
	// 1:在同一个类中调用启动事务的方法不起作用
	// 2:如果在启动事务的方法中嵌套启动事务方法，那么共用最外层事务，只有有一处异常，全部回滚
	// 3:如果外层没有事务，那么事务只作用于方法内，方法异常只回滚方法内
	@Transactional  
	public void testTrans() {
		Person person = new Person();
		person.setId("1");
		person.setName("tom");
		person.setSalary("20000");
		person.setMgr("jack");
		testDAO.update(person);
		tranDemo.testTransaction();
	}
}
