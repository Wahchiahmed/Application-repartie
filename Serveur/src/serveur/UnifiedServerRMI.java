package serveur;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

import service.SI1Service;
import service.SI1ServiceImpl;
import service2.SI2ServiceImpl;
import serviceGlobale.GlobalRevenueServiceImpl;

public class UnifiedServerRMI {

    public static void main(String[] args) {
        try {Scanner scanner = new Scanner(System.in);
            System.out.println("Choose a System Information to serve (SI1 or SI2 or Overall):");
            String choice = scanner.next();

            if (choice.equalsIgnoreCase("SI1")) {
            	LocateRegistry.createRegistry(1012);
                SI1ServiceImpl si1 = new SI1ServiceImpl();
                Naming.rebind("rmi://localhost:1012/SI", si1);
            } else if (choice.equalsIgnoreCase("SI2")) {
            	LocateRegistry.createRegistry(1017);
                SI2ServiceImpl si2 = new SI2ServiceImpl();
                Naming.rebind("rmi://localhost:1017/SI", si2);
            }
            else if (choice.equalsIgnoreCase("Overall")) {
            	LocateRegistry.createRegistry(1019);
            	SI1ServiceImpl si1 = new SI1ServiceImpl();
            	SI2ServiceImpl si2 = new SI2ServiceImpl();
            	GlobalRevenueServiceImpl GR = new GlobalRevenueServiceImpl(si1, si2);
            	Naming.rebind("rmi://localhost:1019/GR", GR);
            }
            else {
                System.out.println("Invalid choice. Exiting.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
