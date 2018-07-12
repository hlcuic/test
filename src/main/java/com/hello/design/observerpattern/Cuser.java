package com.hello.design.observerpattern;

public class Cuser implements Observer {
	
	private String name;
	
	private String msg;
	
	public Cuser(String name){
		this.name = name;
	}

	@Override
	public void update(String msg) {
		this.msg = msg;
		System.out.println("用户："+name+" 收到消息  "+ this.msg);
	}

}
