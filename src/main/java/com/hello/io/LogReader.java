//package cn.com.git.gfets.thread.service;
//
//import cn.com.git.base.util.PropUtil;
//import cn.com.git.gfets.entity.BaseRsp;
//import cn.com.git.gfets.initializer.GfetsServiceInitializer;
//import cn.com.git.gfets.service.BaseRspService;
//import cn.com.git.gfets.util.BeanUtil;
//import imix.ConfigError;
//import imix.DataDictionary;
//import imix.FieldNotFound;
//import imix.InvalidMessage;
//import imix.Message;
//import imix.Message.Header;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.PrintStream;
//import java.io.RandomAccessFile;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//public class LogReader extends Thread {
//	private static final Log logger = LogFactory.getLog(LogReader.class);
//
//	private File logFile = null;
//
//	private File logfile_old = null;
//	private long lastTimeFileSize = 0L;
//
//	private int status = 1;
//
//	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	private BaseRspService baseRspService;
//
//	public BaseRspService getBaseRspService() {
//		return this.baseRspService;
//	}
//
//	public void setBaseRspService(BaseRspService baseRspService) {
//		this.baseRspService = baseRspService;
//	}
//
//	public LogReader(File logFile, File logfile_old) {
//		this.logFile = logFile;
//		this.logfile_old = logfile_old;
//	}
//
//	public void run() {
//		System.out.println("日志服务启动");
//		while (GfetsServiceInitializer.IS_RUNReadLOG)
//			try {
//				logger.debug("日志服务启动");
//				long len = this.logFile.length();
//				if (len == 0L) {
//					try {
//						Thread.sleep(5000L);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				} else {
//					System.out.println("日志服务文件读取到");
//					System.out.println("日志读取到的长度：" + this.lastTimeFileSize);
//
//					//当前文件长度小于最后一次记录日志长度，说明归档了
//					if (len < this.lastTimeFileSize) {
//						logger.info("日志文件当前小于上次记录的大小");
//
//						String endTime = lastTimeDate();
//
//						File logfile1 = this.logfile_old;
//						//读旧文件
//						read(logfile1, this.lastTimeFileSize, "gbk");
//						//读新文件
//						len = readerLast(this.logFile, endTime);
//						this.lastTimeFileSize = len;
//					} else if (this.lastTimeFileSize == 0L) {
//						logger.info("开始读取日志文件");
//						System.out.println("开始从文件尾读取日志文件");
//
//						String endTime = lastTimeDate();
//						System.out.println("开始从文件尾时间：" + endTime);
//						if (endTime == null)
//							this.lastTimeFileSize = len;//服务重启，已经解析过
//						else {
//							this.lastTimeFileSize = readerLast(this.logFile, endTime);
//						}
//
//					} else if (len > this.lastTimeFileSize) {
//						this.lastTimeFileSize = read(this.logFile, this.lastTimeFileSize, "gbk");
//					}
//
//					this.status = 1;
//				}
//			} catch (Exception e) {
//				logger.error("读取文件线程异常2", e);
//				this.status = 3;
//				try {
//					Thread.sleep(5000L);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			} finally {
//				try {
//					Thread.sleep(5000L);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//	}
//
//	public long readerLast(File filename, String lastTime) {
//		RandomAccessFile rf = null;
//		try {
//			rf = new RandomAccessFile(filename, "r");
//			long len = rf.length();
//			long start = rf.getFilePointer();
//			long nextend = start + len - 1L;
//
//			rf.seek(nextend);
//			int c = -1;
//			while (nextend > start) {
//				c = rf.read();
//				if ((c == 10) || (c == 13)) {
//					String line = rf.readLine();
//					if (line != null) {
//						String tmp = new String(line.getBytes("ISO-8859-1"), "gbk");
//						boolean isok = toMessage(tmp, lastTime);
//						if (!isok) {
//							break;
//						}
//					}
//					nextend -= 1L;
//				}
//				nextend -= 1L;
//				rf.seek(nextend);
//				if (nextend == 0L) {
//					String tmp = new String(rf.readLine().getBytes("ISO-8859-1"), "gbk");
//					toMessage(tmp, lastTime);
//				}
//			}
//
//			long l1 = len;
//			return l1;
//		} catch (FileNotFoundException e) {
//			logger.error("读取文件线程异常（readerLast）1", e);
//			this.status = 3;
//		} catch (IOException e) {
//			logger.error("读取文件线程异常（readerLast）2", e);
//			this.status = 3;
//		} catch (ParseException e) {
//			logger.error("读取文件线程异常（readerLast）3", e);
//			this.status = 3;
//		} finally {
//			try {
//				if (rf != null)
//					rf.close();
//			} catch (IOException e) {
//				logger.error("读取文件线程异常（readerLast）4", e);
//				this.status = 3;
//			}
//		}
//		return 0L;
//	}
//
//	public long read(File filename, long filesize, String charset) {
//		RandomAccessFile randomFile = null;
//		try {
//			randomFile = new RandomAccessFile(filename, "r");
//			randomFile.seek(filesize);
//			String tmp = null;
//			long length = 0L;
//			while ((tmp = randomFile.readLine()) != null) {
//				tmp = new String(tmp.getBytes("ISO-8859-1"), charset);
//				//对读到的数据进行解析
//				toMessage(tmp, null);
//				length = randomFile.getFilePointer();
//			}
//			long l1 = length;
//			return l1;
//		} catch (FileNotFoundException e) {
//			logger.error("读取文件线程异常（read）1", e);
//			this.status = 3;
//		} catch (IOException e) {
//			logger.error("读取文件线程异常（read）2", e);
//			this.status = 3;
//		} catch (ParseException e) {
//			logger.error("读取文件线程异常（read）3", e);
//			this.status = 3;
//		} finally {
//			try {
//				if (randomFile != null)
//					randomFile.close();
//			} catch (IOException e) {
//				this.status = 3;
//				logger.error("读取文件线程异常（read）4", e);
//			}
//		}
//
//		return filesize;
//	}
//
//	public boolean toMessage(String tmp, String lastTime) throws ParseException {
//		if ((tmp.indexOf("outgoing") > -1) && ((tmp.indexOf("\00135=S") != -1) || (tmp.indexOf("\00135=8") != -1))) {
//			String filter = PropUtil.get("imix_filter");
//
//			System.out.println(dateFormat.format(new Date()) + "\t" + tmp);
//			logger.info(dateFormat.format(new Date()) + "\t" + tmp);
//			String[] logs = AnalysisLog(tmp);
//			String logtime = logs[0];
//
//			if ((lastTime != null) && (logtime != null)) {
//				Date lastdate = BeanUtil.StrToDate(lastTime, "yyyy-MM-dd hh:mm:ss,SSS");
//				Date logdate = BeanUtil.StrToDate(logtime, "yyyy-MM-dd hh:mm:ss,SSS");
//
//				if (lastdate.compareTo(logdate) > -1) {
//					System.out.println("日志信息  lastdate:" + lastdate + " logdate:" + logdate);
//					return false;
//				}
//			}
//
//			if (logs[1] == null) {
//				logger.error("解析日志异常（toMessage）logs[1]为null,日志：" + tmp);
//			} else {
//				try {
//					Message message = logToMessage(logs[1]);
//					BaseRsp br = new BaseRsp();
//
//					br.setMsgType(message.getHeader().getString(35));
//					br.setFileTs(logtime);
//					br.setMSG(logs[1]);
//					if (message.isSetField(11)) {
//						br.setClOrdId(message.getString(11));
//					}
//					if (message.isSetField(117)) {
//						br.setOrderId(message.getString(117));
//					} else {
//						return true;
//					}
//
//					if ("8".equals(br.getMsgType())) {
//						if ((!message.isSetField(10317)) || (!message.getString(10317).equals("1"))) {
//							return true;
//						}
//					}
//
//					if (("S".equals(br.getMsgType()))
//							&& ((!message.isSetField(537)) || (!message.getString(537).equals("1")))) {
//						return true;
//					}
//
//					br.setStatus("0");
//
//					this.baseRspService.saveBaseRsp(br);
//				} catch (FieldNotFound e) {
//					this.status = 3;
//					logger.error("解析日志异常（toMessage）", e);
//				} catch (NumberFormatException e) {
//					logger.error("解析日志异常（toMessage）日志：" + tmp, e);
//					this.status = 3;
//				} catch (Exception e) {
//					logger.error("解析日志异常（toMessage）日志：" + tmp, e);
//					this.status = 3;
//				}
//			}
//		}
//		return true;
//	}
//
//	public static Message logToMessage(String log) throws ConfigError, InvalidMessage {
//		Message message = null;
//
//		DataDictionary dd = BeanUtil.getInstance().getdataDictionary();
//		message = new Message(log, dd, false);
//
//		return message;
//	}
//
//	public static String[] AnalysisLog(String log) {
//		log = log + "end";
//		String[] logstrs = new String[2];
//		String regex = "(.*?) outgoing(?:.*?)(8=.*?)end";
//		Pattern p = Pattern.compile(regex);
//		Matcher m = p.matcher(log);
//		while (m.find()) {
//			logstrs[0] = m.group(1);
//			String imix = m.group(2);
//			int indexs = imix.indexOf("\00189=");
//			int indexe = imix.indexOf("\00110=");
//
//			if ((indexs == -1) || (indexe == -1)) {
//				logstrs[1] = imix;
//				return logstrs;
//			}
//
//			String startStr = imix.substring(0, indexs + 4);
//			String endStr = imix.substring(indexe, imix.length());
//
//			indexs = imix.indexOf("\00193=");
//			indexe = imix.indexOf("\00189=");
//
//			if ((indexs == -1) || (indexe == -1)) {
//				logstrs[1] = imix;
//				return logstrs;
//			}
//
//			String lenght = imix.substring(indexs + 4, indexe);
//
//			int l = Integer.parseInt(lenght);
//			String repstr = "";
//			for (int i = 0; i < l; i++) {
//				repstr = repstr + "1";
//			}
//			imix = startStr + repstr + endStr;
//			logstrs[1] = imix;
//		}
//		return logstrs;
//	}
//	
//	private String lastTimeDate() {
//		String lasttime = this.baseRspService.getlastTime();
//		if ((lasttime == null) || (lasttime.length() == 0)) {
//			return null;
//		}
//		return lasttime;
//	}
//
//	public int getStatus() {
//		return this.status;
//	}
//
//	public void setStatus(int status) {
//		this.status = status;
//	}
//
////	public static void main(String[] args) {
////		String log = "2017-07-24 15:35:47,918 outgoing [QF/J Session dispatcher: IMIX.1.0:Agent-HTZQ->Hub-RMB-CSTP] (SourceFile:42) INFO  --> IMIX.1.0:CFETS-RMB-CSTP->100147<htzq476001: 8=IMIX.1.0\0019=1729\00135=8\00134=727\00149=CFETS-RMB-CSTP\00152=20170724-07:35:47.918\00156=100147\00157=htzq476001\001115=CFETS-RMB\001627=1\001628=Agent-HTZQ\001630=723\001629=20170724-07:33:36.641\00111=412649500886226009\00117=CBT20170724003888\00132=20000000\00144=99.4895\00148=170013\00154=4\00155=17附息国债13\00160=20170724-15:33:36.363\00164=20170724\00175=20170724\001117=201707240420001750\001119=19960497.26\001120=CNY\001150=F\001159=0.31299\001919=0\00110002=62597.26\00110022=N\00110048=99.8025\00110105=0\00110176=4\00110243=Y6M10D29\00110282=N\00110312=19897900.00\00110317=1\00110318=15:33:36\00110319=1\00110351=Y\00110465=0\00111143=13\001232=1\001233=Yield2\001234=3.6541\001453=2\001448=100371\001452=119\00110601=1\00110602=-0631-5281681\00110603=6\001802=14\001523=山东省威海市宝泉路9号\001803=6\001523=杨静怡\001803=101\001523=威海商行\001803=125\001523=威海市商业银行\001803=124\001523=WHCC\001803=102\001523=70000111000000000018\001803=15\001523=威海市商业银行\001803=23\001523=威海市商业银行\001803=110\001523=313465000010\001803=112\001523=国债登记结算公司\001803=111\001523=Z1011000000\001803=10\001523=威海市商业银行\001803=22\001523=杨静怡\001803=126\001523=CFETS\001803=29\001448=100147\001452=120\00110601=1\00110602=025-83387848\00110603=6\001802=14\001523=南京市江东中路228号华泰证券广场1号楼\001803=6\001523=刘婷\001803=101\001523=华泰证券\001803=125\001523=华泰证券股份有限公司\001803=124\001523=Huatai Securities\001803=102\001523=506658190856\001803=15\001523=华泰证券股份有限公司\001803=23\001523=中国银行江苏省分行营业部(大额支付号（104301003011）)\001803=110\001523=104301003011\001803=112\001523=国债登记结算公司\001803=111\001523=A0059000001\001803=10\001523=华泰证券股份有限公司\001803=22\001523=刘婷\001803=126\001523=CFETS\001803=29\00193=62\00189=???僴???勤???????????夀嬠???搄沀?????嬠???搄沀??????????卂‰???±\004| \004:\021\004@)\00110=003\001";
////
////		System.out.println(log.indexOf("outcoming"));
////		String[] logs = AnalysisLog(log);
////		try {
////			Message message = logToMessage(logs[1]);
////			System.out.println(message.getHeader().getString(35));
////		} catch (FieldNotFound e) {
////			e.printStackTrace();
////		} catch (ConfigError e) {
////			e.printStackTrace();
////		} catch (InvalidMessage e) {
////			e.printStackTrace();
////		}
////	}
//}