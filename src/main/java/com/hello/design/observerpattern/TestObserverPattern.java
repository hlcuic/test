package com.hello.design.observerpattern;

public class TestObserverPattern {
	public static void main(String[] args) {
		
		//公众号
		Wechat wechat = new Wechat();
		
		//a b c 三个用户关注公众号
		Observer a = new Auser("aaa");
		Observer b = new Auser("bbb");
		Observer c = new Auser("ccc");
		wechat.registerObserver(a);
		wechat.registerObserver(b);
		wechat.registerObserver(c);
		// 公众号更新消息
		wechat.setInfo("hello world!");
		
		System.out.println("----------------------------");
		
		//c用户取消关注公众号
		wechat.removeObserver(c);
		wechat.setInfo("hello nihao!");
	}
}
