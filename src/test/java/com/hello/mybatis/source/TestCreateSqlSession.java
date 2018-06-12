package com.hello.mybatis.source;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hello.dao.TestDAO;
import com.hello.test.base.BaseDAOTest;

public class TestCreateSqlSession extends BaseDAOTest{
	
	@Autowired
	private TestDAO dao;
	
	@Test
	public void testCreateSqlSession(){
		System.out.println(dao.listPersons());
	}
}
