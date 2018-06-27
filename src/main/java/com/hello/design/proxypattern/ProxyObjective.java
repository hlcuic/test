package com.hello.design.proxypattern;

public class ProxyObjective implements IObjective{
	
	ObjectiveService service;
	
	ProxyObjective(ObjectiveService service){
		this.service = service;
	}
	
	private void before(){
		
	}

	@Override
	public void execute() {
		//方法前执行切入一些操作
		before();
		service.execute();
		//方法后执行切入一些操作
		after();
	}
	
	private void after(){
		
	}
	
	
}
