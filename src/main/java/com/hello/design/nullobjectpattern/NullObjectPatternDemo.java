package com.hello.design.nullobjectpattern;

public class NullObjectPatternDemo {
	public static void main(String[] args) {
		AbstractCustomer jack = AbstractFactory.getCust("jack");
		AbstractCustomer lili = AbstractFactory.getCust("lili");
		AbstractCustomer tom = AbstractFactory.getCust("tom");
		AbstractCustomer jack11 = AbstractFactory.getCust("jack11");
		System.out.println(jack.getName());
		System.out.println(lili.getName());
		System.out.println(tom.getName());
		System.out.println(jack11.getName());
	}
}
