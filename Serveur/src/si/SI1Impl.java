package si;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class SI1Impl {
    private static final String FILE_PATH = "invoices.txt";

    public SI1Impl() throws RemoteException {
        super();
    }

    public List<Invoice> getSalesInvoices() throws RemoteException {
        List<Invoice> invoices = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] invoiceData = line.split(",");
                if (invoiceData.length == 3) {
                    // Assuming the structure is: code, clientName, amount
                    long code = Long.parseLong(invoiceData[0]);
                    String clientName = invoiceData[1];
                    double amount = Double.parseDouble(invoiceData[2]);

                    Invoice invoice = new Invoice(code, clientName, amount);
                    invoices.add(invoice);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Error retrieving sales invoices.");
        }

        return invoices;
    }
    
    public void addInvoice(long id, String name, double price) throws RemoteException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            // Append the new invoice to the file
            writer.write(id + "," + name + "," + price);
            writer.newLine();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Error adding a new invoice.");
        }
    }
}
