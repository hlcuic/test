package com.hello.test.validate;

import java.util.Arrays;

import org.junit.Test;

import com.hello.model.Person;
import com.hello.util.ValidateUtils;

public class TestValidateParamsByAnnotation {
	
	@Test
	public void testValidateParams() throws Exception{
		Person person = new Person();
		person.setId("1");
		person.setName("tom");
		ValidateUtils.doValidator(person);
	}
	
	@Test
	public void testGetSuperClass(){
		Class<?> clazz = C.class;
		//获取C类父类
		System.out.println("C父类："+clazz.getSuperclass());
		//获取C类父接口
		System.out.println("C父接口:"+Arrays.toString(clazz.getInterfaces()));
	}
}

class A{
	
}

interface B extends D{
	
}

interface D{
	
}

class C extends A implements B{
	
}
