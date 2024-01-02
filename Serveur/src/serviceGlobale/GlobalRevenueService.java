package serviceGlobale;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GlobalRevenueService extends Remote {

	double calculateOverallRevenue() throws RemoteException;
	
}
