package com.hello.mybatis.source;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hello.dao.TestDAO;
import com.hello.test.base.BaseDAOTest;

public class TestCreateSqlSession extends BaseDAOTest{
	
	@Autowired
	private TestDAO dao;
	
	@Test
	public void testQuery(){
		System.out.println(dao.listPersons());
	}
	
	public static void main(String[] args) throws IOException{
		String config = "mybatis-config.xml";
		Reader reader = Resources.getResourceAsReader(config);
		SqlSessionFactoryBuilder sb = new SqlSessionFactoryBuilder();
		//创建sqlSessionFactory的过程中解析mybatis-config配置文件，将xml文件中mapper解析
		SqlSessionFactory sqlSessionFactory = sb.build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		TestDAO testDAO = sqlSession.getMapper(TestDAO.class);
		System.out.println(testDAO.listPersons());
	}
	
}
