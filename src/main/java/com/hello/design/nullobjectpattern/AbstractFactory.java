package com.hello.design.nullobjectpattern;

import java.util.Arrays;
import java.util.List;

public class AbstractFactory {

	private static List<String> customers = Arrays.asList("jack", "tom", "lucy");

	public static AbstractCustomer getCust(String name) {
		for (String customer : customers) {
			if (customer.equalsIgnoreCase(name)) {
				return new RealCustomer(name);
			}
		}
		return new NullCustomer();
	}

}
