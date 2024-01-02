package serviceGlobale;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.Serializable;
import java.util.List;

import service.SI1Service;
import service2.SI2Service;
import si.Invoice;
import si2.TransportsInvoice;

public class GlobalRevenueServiceImpl extends UnicastRemoteObject implements GlobalRevenueService, Serializable {

    private SI1Service si1Service;
    private SI2Service si2Service;

    public GlobalRevenueServiceImpl(SI1Service si1Service, SI2Service si2Service) throws RemoteException {
        super();
        this.si1Service = si1Service;
        this.si2Service = si2Service;
    }

    @Override
    public double calculateOverallRevenue() throws RemoteException {
        double overallRevenue = 0.0;

        // Calculate revenue from SI1 (Flat file)
        List<Invoice> si1Invoices = si1Service.getSalesInvoices();
        for (Invoice invoice : si1Invoices) {
            overallRevenue += invoice.getMontantVersée();
        }

        // Calculate revenue from SI2 (Database)
        List<TransportsInvoice> si2Invoices = si2Service.getTransportInvoices();
        for (TransportsInvoice invoice : si2Invoices) {
            overallRevenue += invoice.getAmount();
        }

        return overallRevenue;
    }
}
