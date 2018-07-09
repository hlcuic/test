package com.hello.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hello.dao.TestDAO;
import com.hello.model.Person;

/**
PROPAGATION_REQUIRED -- 支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择。 
PROPAGATION_SUPPORTS -- 支持当前事务，如果当前没有事务，就以非事务方式执行。 
PROPAGATION_MANDATORY -- 支持当前事务，如果当前没有事务，就抛出异常。 
PROPAGATION_REQUIRES_NEW -- 新建事务，如果当前存在事务，把当前事务挂起。 
PROPAGATION_NOT_SUPPORTED -- 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。 
PROPAGATION_NEVER -- 以非事务方式执行，如果当前存在事务，则抛出异常。 
PROPAGATION_NESTED -- 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则进行与PROPAGATION_REQUIRED类似的操作。 
 * @author Administrator
 *
 */
@Service
public class TransactionDemo{
	
	@Autowired
	private TestDAO testDAO;
	
//	@Transactional //默认RuntimeException或者Error时启动事务
//	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED) //外有则外，没有则内
//	@Transactional(propagation=Propagation.SUPPORTS)//外有则用，没有则不用
//	@Transactional(propagation=Propagation.MANDATORY)//必须在一个父类事务中运行
//	@Transactional(propagation=Propagation.REQUIRES_NEW)//将父类事务挂起，启动内部事务,抛出异常，父类也会回滚
//	@Transactional(propagation=Propagation.NOT_SUPPORTED)//不支持事务，外部事务到这里失效，走出方法后再开启事务
//	@Transactional(propagation=Propagation.NEVER) //不支持事务调用，调用报错
	@Transactional(propagation=Propagation.NESTED)  //嵌套事务,隶属于父类事务
	public void testTransaction() {
		Person person = new Person();
		person.setName("tom");
		person.setSalary("20000");
		person.setMgr("jack");
		testDAO.add(person);
		int i = 1 / 0;
	}
	
}
