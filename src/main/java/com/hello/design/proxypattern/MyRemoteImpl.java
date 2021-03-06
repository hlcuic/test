package com.hello.design.proxypattern;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {

	private static final long serialVersionUID = 1L;

	public MyRemoteImpl() throws RemoteException {
		super();
	}

	@Override
	public String sayHello() throws RemoteException {
		return "Server says ,'Hey'";
	}

	public static void main(String[] args) {
		try {
			MyRemote myRemote = new MyRemoteImpl();
			Naming.rebind("RemoteHello", myRemote);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
