package com.hello.io;

public class MSDOrderReq {
	
	private String mailno;
	
	private String txlogisticid;
	

	public String getMailno() {
		return mailno;
	}

	public void setMailno(String mailno) {
		this.mailno = mailno;
	}

	public String getTxlogisticid() {
		return txlogisticid;
	}

	public void setTxlogisticid(String txlogisticid) {
		this.txlogisticid = txlogisticid;
	}

	@Override
	public String toString() {
		return "MSDOrderReq [mailno=" + mailno + ", txlogisticid=" + txlogisticid + "]";
	}
	
	
}
