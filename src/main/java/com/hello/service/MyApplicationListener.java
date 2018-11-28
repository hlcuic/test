package com.hello.service;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.hello.model.MySpringEvent;

@Component
public class MyApplicationListener implements ApplicationListener<MySpringEvent>,BeanNameAware{

    public void onApplicationEvent(MySpringEvent event) {
        if(event instanceof MySpringEvent){
            //执行一些自定义操作,这里打印一下。
            MySpringEvent mySpringEvent=(MySpringEvent)event;
            System.out.println("-------------------- "+mySpringEvent.getName()+"---------------");
        }
    }

	@Override
	public void setBeanName(String arg0) {
		System.out.println("-------------"+arg0+"-------------");
	}

}
