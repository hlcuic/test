package com.hello.test.validate;

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
	
}
