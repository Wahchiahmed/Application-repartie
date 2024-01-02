package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import si.Invoice;
import si.SI1Impl;

public class SI1ServiceImpl extends UnicastRemoteObject implements SI1Service{
	
	private SI1Impl si ;
	
	public SI1ServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		this.si = new SI1Impl();
	}
	
	
	@Override
	public List<Invoice> getSalesInvoices() throws RemoteException {
		
		return si.getSalesInvoices();
	}


	@Override
	public void addInvoice(long id, String name, double price) throws RemoteException {
		si.addInvoice(id, name, price);
	}

	
	
	
}
