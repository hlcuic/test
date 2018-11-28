package com.hello.service;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class CDRService implements InitializingBean{

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("--------CDRService-------");
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){

			@Override
			public void run() {
				System.out.println("--------timer start------------");
			}
			
		}, 2000, 5000);
	}

}
