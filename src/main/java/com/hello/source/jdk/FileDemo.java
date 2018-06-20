package com.hello.source.jdk;

import java.io.File;

public class FileDemo {
	public static void main(String[] args) {
		File file = new File(".");
		file.list();
	}
}
