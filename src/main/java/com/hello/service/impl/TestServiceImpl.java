package com.hello.service.impl;

import org.springframework.stereotype.Service;
import com.hello.service.ItestService;

@Service
public class TestServiceImpl implements ItestService{

	@Override
	public String send() {
		return "你好!";
	}

}
