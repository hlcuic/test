package com.hello.source.jdk;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Arrays;

public class FileDemo {
	public static void main(String[] args) {
		File file = new File(".");
		testPrintFileName(file);
		testPrintFile(file);
	}
	
	private static void testPrintFile(File file){
		//获取该目录下所有的文件
		File[] files = file.listFiles();
		System.out.println(Arrays.toString(files));
		//根据文件过滤器获取文件（回调方法）
		File[] files2 = file.listFiles(new FileFilter(){

			@Override
			public boolean accept(File pathname) {
				return pathname.getName().endsWith(".xml");
			}
			
		});
		System.out.println(Arrays.toString(files2));
		
		//根据文件名称进行过滤
		File[] files3 = file.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".xml");
			}
		});
		System.out.println(Arrays.toString(files3));
		
	}

	private static void testPrintFileName(File file) {
		// 打印当前目录下的文件名称
		System.out.println(Arrays.toString(file.list()));
		// 过滤文件名，打印满足条件的文件名称
		String[] fileNames = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".xml");
			}
		});
		System.out.println(Arrays.toString(fileNames));
	}
}
