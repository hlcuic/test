package cn.com.git.gfets.thread.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.com.git.gfets.entity.AccountInfo;
import cn.com.git.gfets.entity.BaseRsp;
import cn.com.git.gfets.entity.CashBond;
import cn.com.git.gfets.entity.TransNotice;
import cn.com.git.gfets.initializer.GfetsServiceInitializer;
import cn.com.git.gfets.service.BaseRspService;
import cn.com.git.gfets.service.CashBondService;
import cn.com.git.gfets.service.TransNoticeService;
import cn.com.git.gfets.spotbond.vo.NoPartyID;
import cn.com.git.gfets.spotbond.vo.NoStipulation;
import cn.com.git.gfets.spotbond.vo.NopartySubID;
import cn.com.git.gfets.util.BeanUtil;
import cn.com.git.gfets.util.ConstantsUtil;
import imix.DataDictionary;
import imix.FieldNotFound;
import imix.Group;
import imix.InvalidMessage;
import imix.Message;

public class AnalysisImixJob extends Thread {

	private static final Log logger = LogFactory.getLog(AnalysisImixJob.class);
	
	private int status =1 ; 
	//原始报文信息
	private  BaseRspService baseRspService;
	//流水
	private CashBondService cashBondService; 
	//成交回报
	private  TransNoticeService transNoticeService;
	
	public AnalysisImixJob(BaseRspService baseRspServie,CashBondService cashBondService,TransNoticeService transNoticeService){
		this.baseRspService = baseRspServie;
		this.cashBondService = cashBondService;
		this.transNoticeService = transNoticeService;
	}
	
	@Override
	public void run() {
		while(GfetsServiceInitializer.IS_ANALYSIS){
			
			//logger.info("------------------------------AnalysisImixJob is running-----------------------------------");
			try{
				//1:查询
				List<BaseRsp>  rsplist = baseRspService.findHandleDate();

				if(rsplist != null){
					for(BaseRsp rsp :rsplist){
						String imix = rsp.getMSG();
						String msgType = rsp.getMsgType();

						Message message = logToMessage(imix);

						if(ConstantsUtil.IMIX_MSG_TYPE_8.equals(msgType)){
							executionReport(message);
							rsp.setStatus("1");
							rsp.setLastUpdateTs(new Timestamp(System.currentTimeMillis()));
							baseRspService.update(rsp);
						}else
							if(ConstantsUtil.IMIX_MSG_TYPE_S.equals(msgType)){
								analysisQuote(message);
								rsp.setStatus("1");
								rsp.setLastUpdateTs(new Timestamp(System.currentTimeMillis()));
								baseRspService.update(rsp);
							}
					}
				}
				status = 1;
			}catch(Exception e){
				logger.error("解析报文异常",e );
				status = 3;
			}finally {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		

	}
	
	/**
	 * 成交报价信息
	 * @param message
	 * @throws FieldNotFound 
	 */
	private void executionReport(Message message) throws FieldNotFound{
		TransNotice tn = new TransNotice();
		
		if(message.isSetField(11))//参考编号
			tn.setClOrdID(message.getString(11));
		if(message.isSetField(117))//报价编号
			tn.setQuoteId(message.getString(117));
		if(message.isSetField(48)){//债券code
			tn.setSecurityId(message.getString(48));
		}
		if(message.isSetField(55)){//债券名称
			tn.setSymbol(message.getString(55));
		}
		if(message.isSetField(17)){//成交编号
			tn.setExecId(message.getString(17));
		}
		if(message.isSetField(37)){//订单编号
			tn.setOrderId(message.getString(37));
		}
		if(message.isSetField(10471)){//请求报价编号
			tn.setReference(message.getString(10471));
		}
		if(message.isSetField(10176)){//市场标示
			tn.setMarketIndicator(message.getString(10176));
		}
		if(message.isSetField(54)){//交易方向
			tn.setSide(message.getString(54));
		}
		if(message.isSetField(10317)){//报价类型
			tn.setTradeMethod(message.getString(10317));
		}
		if(message.isSetField(32)){//券面总额
			tn.setLastqty(new BigDecimal(message.getString(32)));
		}
		if(message.isSetField(44)){//净价
			tn.setPrice(new BigDecimal(message.getString(44)));
		}
		if(message.isSetField(10048)){//全价
			tn.setDirtyPrice(new BigDecimal(message.getString(10048)));
		}
		if(message.isSetField(159)){//应计利息
			tn.setInterestAmt(new BigDecimal(message.getString(159)));
		}
		if(message.isSetField(10002)){//应计总利息
			tn.setInterrestTotalAmt(new BigDecimal(message.getString(10002)));
		}
		if(message.isSetField(60)){//业务发生时间
			tn.setTransTime(message.getString(60));
		}
		if(message.isSetField(10312)){//交易金额
			tn.setTradeCashAmt(new BigDecimal(message.getString(10312)));
		}
		if(message.isSetField(64)){//结算日期
			tn.setSettlDate(message.getString(64));
		}
		if(message.isSetField(119)){//结算金额
			tn.setSettlCurrAmt(new BigDecimal(message.getString(119)));
		}
		if(message.isSetField(919)){//结算方式
			tn.setDeliveryType(message.getString(919));
		}
		if(message.isSetField(11143)){//清算方式
			tn.setClearMethod(message.getString(11143));
		}
		if(message.isSetField(10022)){//应急标示
			tn.setContingencyIndicator(message.getString(10022));
		}
		if(message.isSetField(10243)){//待偿期
			tn.setTermToMaturity(message.getString(10243));
		}
		if(message.isSetField(75)){//成交日期
			tn.setTradeDate(message.getString(75));
		}
		if(message.isSetField(10318)){//成交时间
			tn.setTradeTime(message.getString(10318));
		}
		if(message.isSetField(10319)){//成交类别
			tn.setTradeType(message.getString(10319));
		}
		if(message.isSetField(10465)){//数据标示
			tn.setDataIndicator(message.getString(10465));
		}
		if(message.isSetField(10105)){//成交单动作状态
			tn.setDealTransType(message.getString(10105));
		}
		if(message.isSetField(58)){//备注
			tn.setText(message.getString(58));
		}
		if(message.isSetField(150)){//成交状态
			tn.setExecType(message.getString(150));
		}
		
		Group sgroup = new Group(232, 0);
		if(message.isSetField(232)){
			int num = message.getInt(232);
			for(int i=1;i<=num;i++){
				message.getGroup(i, sgroup);
				if(sgroup.isSetField(233))
				tn.setStipulationType(sgroup.getString(233));
				if(sgroup.isSetField(234))
					tn.setStipulationValue(new BigDecimal(sgroup.getString(234)));
			}
			
		}
		
		
		tn.setCreatetTs(new Timestamp(System.currentTimeMillis()));
		
		List<AccountInfo> sccountInfos = new ArrayList<AccountInfo>();
		Group group = new Group(453, 1);
		if(message.isSetField(453)){
			int num = message.getInt(453);
			for(int i=1;i<=num;i++){
				message.getGroup(i, group);
				AccountInfo ai = new AccountInfo();
				if(group.isSetField(448))//机构16位机构号
					ai.setPartyId(group.getString(448));
				if(group.isSetField(452))//119 买入方  120 卖出方
//					ai.setSource(group.getString(452));
					ai.setSource("交易中心会员");
				ai.setPartyRole(group.getString(452));
				
				if(group.isSetField(10601)){
					int num2 = group.getInt(10601);
					Group group2 = new Group(10601, 0);
					for(int j=1;j<=num2;j++){
						group.getGroup(j, group2);
						if(group2.isSetField(10602)&&group2.isSetField(10603)){
							 String type = group2.getString(10603);
							   String value = group2.getString(10602);
							   if("6".equals(type)){//电话
								   ai.setTel(value);
							   }
						}
					}
				}
				
				if(group.isSetField(802)){
					int num2 = group.getInt(802);
					Group group2 = new Group(802, 0); 
					for(int j=1;j<=num2;j++){
						group.getGroup(j, group2);
						if(group2.isSetField(803)&&group2.isSetField(523)){
							   String type = group2.getString(803);
							   String value = group2.getString(523);
							   if("126".equals(type)){//交易员名称
								   ai.setTraderName(value);
							   }else if("101".equals(type)){
								   ai.setTraderChnName(value);
							   }
							   else if("10".equals(type)){//托管账号
								   ai.setCustodianAccNo(value);
							   } else if("111".equals(type)){//托管机构
								   ai.setCustodianName(value);
							   } else if("22".equals(type)){//托管账户户名
								   ai.setCustodianAccName(value);
							   }
							   else if("110".equals(type)){//资金开户行
								   ai.setSettlBankName(value);
							   }else if("112".equals(type)){//资金开户行联行行号
								   ai.setBankCode(value);
							   }else if("15".equals(type)){//资金账号
								   ai.setSettlAccNo(value);
							   }else if("23".equals(type)){//资金账户户名
								   ai.setSettlAccName(value);
							   }else if("6".equals(type)){
								   ai.setAddrInfo(value);
							   }else if("29".equals(type)){
								   ai.setSource(value);
							   }
						}
					}
					
					
				}
				sccountInfos.add(ai);
			}
		}
		
		transNoticeService.saveAll(tn, sccountInfos);
	}
	
	/**
	 * 对话报价的流水解析
	 * @param message
	 * @throws FieldNotFound 
	 */
	private void analysisQuote(Message message) throws FieldNotFound{
		CashBond bond = new CashBond();
		bond.setType("RSP");
		if(message.isSetField(11))//参考编号
			bond.setClOrdID(message.getString(11));
		if(message.isSetField(117))//报价编号
			bond.setQuoteID(message.getString(117));
		if(message.isSetField(48)){//债券code
			bond.setSecurityID(message.getString(48));
		}
		if(message.isSetField(55)){//债券名称
			bond.setSymbol(message.getString(55));
		}
		if(message.getHeader().isSetField(35)){
			bond.setMsgtype(message.getHeader().getString(35));
		}
		if(message.isSetField(10272)){//报文动作
			bond.setTransType(message.getString(10272));
		}
		if(message.isSetField(10176)){//市场
			bond.setMarketIndicator(message.getString(10176));
		}
		if(message.isSetField(10022)){//应急标示
			bond.setContingencyIndicator(message.getString(10022));
		}
		if(message.isSetField(10271)){//报价时间
			bond.setTransTime(message.getString(10271));
		}
		if(message.isSetField(62)){//报价有效期
			bond.setValidTime(message.getString(62));
		}
		if(message.isSetField(54)){//交易方向
			bond.setSide(message.getString(54));
		}
		if(message.isSetField(537)){//报价类型
			bond.setQuoteType(message.getString(537));
		}
		if(message.isSetField(58)){//补充条款
			bond.setText(message.getString(58));
		}
		if(message.isSetField(44)){//净价
			bond.setPrice(message.getDecimal(44));
		}
		if(message.isSetField(38)){//券面总额
			bond.setQty(message.getDecimal(38));
		}
		if(message.isSetField(297)){//状态 16：正常  5：拒绝 19 ：撤销
			bond.setStatus(message.getString(297));
		}
		if(message.isSetField(10048)){//全价
			bond.setDirtyPrice(message.getDecimal(10048));
		}
		if(message.isSetField(10312)){//交易金额
			bond.setTradeCashAmt(message.getDecimal(10312));
		}
		if(message.isSetField(10002)){//应计利息总额
			bond.setAccruedInterrestTotalAmt(message.getDecimal(10002));
		}
		if(message.isSetField(159)){//应计利息
			bond.setAccruedInterrestAmt(message.getDecimal(159));
		}
		if(message.isSetField(63)){//清算速度
			bond.setSettlType(message.getString(63));
		}
		if(message.isSetField(119)){//结算金额
			bond.setSettlCurrAmt(message.getString(119));
		}
		if(message.isSetField(919)){//结算方式
			bond.setDeliveryType(message.getString(919));
		}
		if(message.isSetField(11143)){//结算方式
			bond.setClearingMethod(message.getString(11143));
		}
		
		
		//收益率
		Group group =  null;
		List<NoStipulation> NoStipulations = new ArrayList<NoStipulation>();
		if(message.isSetField(232)){
			int num = message.getInt(232);
			for(int i=1;i<=num;i++){
				group = new Group(232, 0);
				message.getGroup(i, group);
				NoStipulation no = new NoStipulation();
				if (group.isSetField(233)) {
					no.setStipulationType(group.getString(233));
				}
				if(group.isSetField(234)){
					no.setStipulationValue(group.getDecimal(234));
				}
				NoStipulations.add(no);
			}
		}
		
		
		//参与机构信息
		List<NoPartyID> noPartyIDs = new ArrayList<NoPartyID>();
		if(message.isSetField(453)){
			int num = message.getInt(453);
			group = new Group(453, 1);
			for(int i=1;i<=num;i++){
				message.getGroup(i, group);
				NoPartyID  no = new NoPartyID();
				if(group.isSetField(448)){
					no.setPartyID(group.getString(448));
				}
				if(group.isSetField(452)){
					no.setPartyRole(group.getString(452));
				}
				
				Group group2 = null ;
				List<NopartySubID> nopartySubIDs = new ArrayList<NopartySubID>();
				if(group.isSetField(802)){
					int roles = group.getInt(802);
					group2 = new Group(802, 0);
					for(int j=1;j<=roles;j++){
						group.getGroup(j, group2);
						NopartySubID nops = new NopartySubID();
						
						if(group2.isSetField(523)){
							nops.setPartySubID(group2.getString(523));
						}
						if(group2.isSetField(803)){
							nops.setPartySubIDType(group2.getString(803));
						}
						nopartySubIDs.add(nops);
					}
				}
				no.setNopartySubIDs(nopartySubIDs);
				noPartyIDs.add(no);
			}
		}
		
		cashBondService.saveAll(bond, noPartyIDs, NoStipulations);
	}
	
	/**
	 * 用imix下行接口jar包分解日志信息
	 * @param log
	 * @return
	 */
	public  Message logToMessage(String log){

		DataDictionary dd;
		Message message = null;
		try {

			dd =BeanUtil.getInstance().getdataDictionary();
			logger.info("log:"+log);
			message = new Message(log,dd,false);

		}  catch (NumberFormatException e) {
			status = 3;
			logger.error("解析日志异常（logToMessage）", e);
		} catch (InvalidMessage e) {
			status = 3;
			logger.error("解析日志异常（logToMessage）", e);
		}

		return message;

	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
	
}
