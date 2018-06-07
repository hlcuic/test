package com.hello.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class WriteThread implements Runnable {

	private static File file;

	@PostConstruct
	public void init() {
		file = new File("d:/client.log");
		Thread t = new Thread(this, "写线程");
		t.start();
	}

	public static void main(String[] args) {
		// run1();
		Pattern p = Pattern.compile("\\-");
		Matcher m = p.matcher("123-32323-4343");
		while (m.find()) {
			System.out.println(m.group());
		}
	}

	// @Override
	public static void run1() {
		BufferedWriter bw = null;
		try {
			file = new File("d:/client.log");
			FileOutputStream out = new FileOutputStream(file, true);
			bw = new BufferedWriter(new OutputStreamWriter(out));
			while (true) {
				try {
					bw.write("i love you 中国 " + System.currentTimeMillis() + "\n");
					bw.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				// try {
				// Thread.sleep(3000);
				// } catch (InterruptedException e) {
				// e.printStackTrace();
				// }
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != bw) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
