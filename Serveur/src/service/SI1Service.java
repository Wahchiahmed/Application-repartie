package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import si.Invoice;

public interface SI1Service extends Remote {
	
    List<Invoice> getSalesInvoices() throws RemoteException;
    void addInvoice(long id, String name, double price) throws RemoteException;
}

