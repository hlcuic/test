package com.hello.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.google.gson.Gson;

@Component
public class ShopeeLogReader extends Thread implements InitializingBean {
	
	private static final Log LOG = LogFactory.getLog(ShopeeLogReader.class);
	
	private File logFile;
	
	private long lastTimeFileSize; // 上次文件大小
	
	private String monitoringStr = "com.szmsd.action.shopee.OrderAction - J&T SHOPEE标准下单";
	
	private Gson gson = new Gson();
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.logFile = new File("d:/client.log");
		this.start();
	}

	/**
	 * 实时读取日志文件
	 */
	@SuppressWarnings("resource")
	public void run() {
		while (true) {
			try {
				RandomAccessFile randomFile = new RandomAccessFile(logFile, "r");
				randomFile.seek(lastTimeFileSize);
				String tmp = null;
				while ((tmp = randomFile.readLine()) != null) {
					tmp = new String(tmp.getBytes("ISO-8859-1"));
					System.out.println(tmp);
					shopeeOrderLog(tmp);
				}
				lastTimeFileSize = randomFile.length();
			} catch (IOException e) {
				LOG.error("",e);
			}
			try {
				Thread.sleep(500);
				System.out.println("扫描...........");
			} catch (InterruptedException e) {
				LOG.error("",e);
			}
		}
	}

	/**
	 * @Title: shopeeOrderLog
	 * @Description: 监控格式=com.szmsd.action.shopee.OrderAction - J&T
	 *               SHOPEE标准下单[请求时间]==>2017-06-09
	 *               11:36:58433[请求参数]==>{"sendstarttime":"2017-05-28
	 *               18:00:00","txlogisticid":"SHOPEE-TEST0008","mailno":"JS0000000608","weight":0.5,"ordertype":1,"logisticproviderid":"JNT","servicetype":1,"eccompanyid":"SHOPEE","sendendtime":"2017-05-28
	 *               23:00:00","createordertime":"2017-06-01
	 *               13:06:48","sender":{"phone":"6285778005338","name":"ivat /
	 *               Mastur
	 *               Jamallulail","city":"JAKARTA","mobile":"6285778005338","prov":"DKI
	 *               JAKARTA","area":"CILINCING","address":"kp bambu kuning
	 *               jalan kutilang rt 12 rw 02 no 47 Kel
	 *               marunda","postcode":"000"},"items":[{"itemname":"M","itemsvalue":210000,"number":1}],"customerid":"123456789","goodsvalue":0,"receiver":{"phone":"6282186006003","name":"Rangga
	 *               Oktariza Armando","city":"PANGKAL
	 *               PINANG","mobile":"6282186006003","prov":"BANGKA
	 *               BELITUNG","area":"BUKIT INTAN","address":"Jl Tapak Dewa
	 *               Asrama Brimob no
	 *               01","postcode":"33147"}}[响应结果]==>{"logisticproviderid":"JT","responseitems":[{"txlogisticid":"SHOPEE-TEST0008","mailno":"JS0000000608","success":"true","reason":""}]}[下单用时]==>105
	 * @param tmp
	 * @author Li Chao
	 * @date 2017年6月9日
	 */
	public void shopeeOrderLog(String tmp) {
		if (!StringUtils.isEmpty(tmp)) {
			if (tmp.indexOf(monitoringStr) != -1) {
				// 请求时间
				String reqBeginDate = tmp.substring(tmp.indexOf("[请求时间]==>") + "[请求时间]==>".length(),
						tmp.indexOf("[请求参数]==>"));
				// 请求内容
				String logistics_interface = tmp.substring(tmp.indexOf("[请求参数]==>") + "[请求参数]==>".length(),
						tmp.indexOf("[响应结果]==>"));
				// 请求结果
				String resResult = tmp.substring(tmp.indexOf("[响应结果]==>") + "[响应结果]==>".length(),
						tmp.indexOf("[下单用时]==>"));
				// 请求用时
				String totalDate = tmp.substring(tmp.indexOf("[下单用时]==>") + "[下单用时]==>".length());

				LOG.info("reqBeginDate：" + reqBeginDate + "logistics_interface：" + logistics_interface + "resResult："
						+ resResult + "totalDate：" + totalDate);

				MSDOrderReq orderReq = gson.fromJson(logistics_interface, MSDOrderReq.class);

				String executeSql = "insert into tab_电商日志表(id, 请求时间, 订单号, 运单编号, 请求来源, 日志内容, ip号, 响应时间, 反馈结果, 创建时间)"
						+ "values (S_电商日志表序列.NEXTVAL, '" + reqBeginDate + "', '" + orderReq.getTxlogisticid() + "', '"
						+ orderReq.getMailno() + "', 'SHOPEE', '" + logistics_interface + "', '', '" + totalDate
						+ "', '" + resResult + "',sysdate)";

				LOG.info("执行的sql语句===>" + executeSql);

				Const.setDBExecuteSQL(executeSql);
			}
		}
	}

}
