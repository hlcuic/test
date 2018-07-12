package com.hello.design.observerpattern;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

public class Wechat implements Observerable {
	
	List<Observer> observerList;
	
	String msg;
	
	public Wechat(){
		observerList = new ArrayList<>();
	}

	@Override
	public void registerObserver(Observer observer) {
		observerList.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		if(!CollectionUtils.isEmpty(observerList)){
			observerList.remove(observer);
		}
	}

	@Override
	public void notifyObserver() {
		for(Observer observer:observerList){
			observer.update(msg);
		}
	}
	
	public void setInfo(String msg){
		System.out.println("服务器更新数据："+msg);
		this.msg = msg;
		notifyObserver();
	}

}
