package si2;

import java.io.Serializable;

public class TransportsInvoice implements Serializable {

	private static final long serialVersionUID = 7519759825467674176L;
    private int id;
    private String clientName;
    private String vehicleType;
    private double amount;
    
	public TransportsInvoice(int id, String clientName, String vehicleType, double amount) {
		super();
		this.id = id;
		this.clientName = clientName;
		this.vehicleType = vehicleType;
		this.amount = amount;
	}
	

	public TransportsInvoice() {
		super();
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
        return "TransportInvoice{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", amount=" + amount +
                '}';
	}
    
}
