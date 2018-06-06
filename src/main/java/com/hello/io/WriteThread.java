package com.hello.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class WriteThread implements Runnable {

	private File file;

	@PostConstruct
	public void init() {
		file = new File("d:/client.log");
		Thread t = new Thread(this, "写线程");
		t.start();
	}

	@Override
	public void run() {
		BufferedWriter bw = null;
		try {
			FileOutputStream out = new FileOutputStream(file, true);
			bw = new BufferedWriter(new OutputStreamWriter(out));
			while (true) {
				try {
					bw.write("i love you 中国 " + System.currentTimeMillis());
					bw.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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

}
