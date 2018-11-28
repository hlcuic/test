package com.hello.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.hello.model.MySpringEvent;
import com.hello.service.ItestService;

@Service
public class TestServiceImpl implements ItestService,ApplicationContextAware{

	@Override
	public String send() {
		return "你好!";
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		arg0.publishEvent(new MySpringEvent("source", "Tom"));
	}

}
