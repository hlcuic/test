package com.hello.spring.source;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.transaction.TestTransaction;

import com.hello.dao.TestDAO;
import com.hello.proxy.jdk.Iwelcome;
import com.hello.transaction.TransDemo;
import com.hello.transaction.TransactionDemo;

public class TestCreateSpringContext {
	public static void main(String[] args) {
		String config = "applicationContext.xml";
		
		//解析xml配置文件，创建beanDefinition对象,put(beanId,beanDefinition),注册
		ApplicationContext ac = new ClassPathXmlApplicationContext(config);
		
		//从注册map中取出beanDefinition对象
//		TestDAO contr = (TestDAO) ac.getBean("testDAO");
//		System.out.println(contr.listPersons());
//		Iwelcome wel = (Iwelcome) ac.getBean("welcome");
//		wel.sayHello();
//		wel.sayBye();
//		wel.test();
		TransDemo testTransaction =  (TransDemo) ac.getBean("transDemo");
		testTransaction.testTrans();
	}
	
}
