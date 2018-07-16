package com.hello.design.iteratorpattern;

public class IteratorPatterDemo {
	public static void main(String[] args) {
		NameRepository repo = new NameRepository();
		for(Iterator it = repo.iterator();it.hasNext();){
			String name=(String) it.next();
			System.out.println(name);
		}
	}
}
