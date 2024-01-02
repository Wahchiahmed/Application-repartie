package client;

import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

import service.SI1Service;
import service2.SI2Service;
import serviceGlobale.GlobalRevenueService;
import si.Invoice;
import si2.TransportsInvoice;

public class UnifiedClientRMI {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Choose a System Information (SI1 or SI2 or Overall):");
            String choice = scanner.next();

            if (choice.equalsIgnoreCase("SI1")) {
                SI1Service si1Stub = (SI1Service) Naming.lookup("rmi://localhost:1012/SI");
                handleSI1(si1Stub, scanner);
            } else if (choice.equalsIgnoreCase("SI2")) {
                SI2Service si2Stub = (SI2Service) Naming.lookup("rmi://localhost:1017/SI");
                handleSI2(si2Stub, scanner);
            }
            else if (choice.equalsIgnoreCase("Overall")) {
            	GlobalRevenueService GrStub = (GlobalRevenueService) Naming.lookup("rmi://localhost:1019/GR");
            	System.out.println("Le Revenue globale : "+GrStub.calculateOverallRevenue());
            }
            else {
                System.out.println("Invalid choice. Exiting.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleSI1(SI1Service si1Stub, Scanner scanner) {
        try {
            System.out.println("Welcome to SI1!");
            List<Invoice> invoices = si1Stub.getSalesInvoices();

            for (Invoice invoice : invoices) {
                System.out.println(invoice.toString());
            }

            System.out.println("Do you want to add an invoice? (yes/no)");
            String response = scanner.next();

            if (response.equalsIgnoreCase("yes")) {
                System.out.println("Enter the code:");
                Long code = scanner.nextLong();
                System.out.println("Enter your name:");
                String name = scanner.next();
                System.out.println("Enter the amount:");

             // Check if the next token is a valid double
             while (!scanner.hasNextDouble()) {
                 System.out.println("Invalid input. Please enter a valid number.");
                 scanner.next(); // Consume the invalid input
             }

             double amount = scanner.nextDouble();
                si1Stub.addInvoice(code, name, amount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleSI2(SI2Service si2Stub, Scanner scanner) {
        try {
            System.out.println("Welcome to SI2!");
            List<TransportsInvoice> invoices = si2Stub.getTransportInvoices();

            for (TransportsInvoice invoice : invoices) {
                System.out.println(invoice.toString());
            }

            System.out.println("Do you want to add a transport invoice? (yes/no)");
            String response = scanner.next();

            if (response.equalsIgnoreCase("yes")) {
                System.out.println("Enter your name:");
                String name = scanner.next();
                System.out.println("Enter the vehicle type:");
                String type = scanner.next();
                System.out.println("Enter the amount:");
                double amount = scanner.nextDouble();

                si2Stub.addTransportInvoice(name, type, amount);
                System.out.println("Successfuly added");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
