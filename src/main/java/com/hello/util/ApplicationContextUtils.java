
package com.hello.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextUtils implements ApplicationContextAware{
	
	private static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		ApplicationContextUtils.context = context;
	}
	
	public static ApplicationContext getApplicationContext(){
		return context;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName){
		return (T) context.getBean(beanName);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<?> clazz){
		return (T) context.getBean(clazz);
	}
	
}
