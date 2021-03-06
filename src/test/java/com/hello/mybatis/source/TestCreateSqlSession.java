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
		/**
		 * 创建sqlSessionFactory的过程中解析mybatis-config配置文件，
		 * 将xml文件中mapper解析,将命名空间对应类class和对应的代理工厂对象缓存
		 */
		SqlSessionFactory sqlSessionFactory = sb.build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		/**
		 * 根据类class从缓存中取出代理工厂对象，newInstance()生成代理对象，当调用接口时，实际调用的
		 * 代理对象方法invoke
		 */
		TestDAO testDAO = sqlSession.getMapper(TestDAO.class);
		System.out.println(testDAO.listPersons());
//		System.out.println(new BigDecimal("1.2123232E3").intValue());
	}
	
}
