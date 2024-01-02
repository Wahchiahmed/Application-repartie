package si2;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SI2Impl  {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/transport";
    private static final String DB_USER = "wahchi";
    private static final String DB_PASSWORD = "wahchywahchy12";

    public SI2Impl() throws RemoteException {
        super();
    }

    public List<TransportsInvoice> getTransportsInvoices() throws RemoteException {
        List<TransportsInvoice> transportInvoices = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM TransportInvoices");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                TransportsInvoice invoice = new TransportsInvoice();
                invoice.setId(resultSet.getInt("id"));
                invoice.setClientName(resultSet.getString("clientName"));
                invoice.setVehicleType(resultSet.getString("vehicleType"));
                invoice.setAmount(resultSet.getDouble("amount"));

                transportInvoices.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error retrieving transport invoices from the database.");
        }

        return transportInvoices;
    }

    public void addTransportInvoice(String clientName, String vehicleType, double amount) throws RemoteException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO TransportInvoices (clientName, vehicleType, amount) VALUES (?, ?, ?)")) {

            statement.setString(1, clientName);
            statement.setString(2, vehicleType);
            statement.setDouble(3, amount);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error adding a new transport invoice to the database.");
        }
    }
}
