package com.hello.design.proxypattern;

public class TestProxyService {
	public static void main(String[] args) {
		ObjectiveService target = new ObjectiveService();
		IObjective proxy = new ProxyObjective(target);
		proxy.execute();
	}
}
