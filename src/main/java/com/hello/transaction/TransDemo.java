package com.hello.transaction;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hello.dao.TestDAO;
import com.hello.model.Person;

@Service
public class TransDemo {
	
	@Autowired
	private TestDAO testDAO;
	
//	@Autowired
//	private TransactionDemo tranDemo;
//	
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
//		tranDemo.testTransaction();
	}
	
	public List<Person> query(){
		System.out.println("------------------------query data from db------------------------");
		return testDAO.listPersons();
	}
	
	public void add(){
		long start = System.currentTimeMillis();
		List<Person> list =new ArrayList<>(); 
		for(int i=0;i<20000;i++){
			Person person = new Person();
			person.setName("tom"+i);
			person.setSalary("10000");
			list.add(person);
		}
		testDAO.addList(list);
		long end = System.currentTimeMillis();
		System.out.println("插入100000条数据，耗时 "+(end-start)+" ms");
	}
}
