package si;

import java.io.Serializable;

public class Invoice implements Serializable {

	private long CodeFacture ;
	private String nomClient;
	private double MontantVersée;
	
	public Invoice(long codeFacture, String nomClient, double montantVersée) {
		super();
		this.CodeFacture = codeFacture;
		this.nomClient = nomClient;
		this.MontantVersée = montantVersée;
	}

	public Invoice() {
		super();
	}

	public long getCodeFacture() {
		return CodeFacture;
	}

	public void setCodeFacture(long codeFacture) {
		CodeFacture = codeFacture;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public double getMontantVersée() {
		return MontantVersée;
	}

	public void setMontantVersée(double montantVersée) {
		MontantVersée = montantVersée;
	}
	@Override
    public String toString() {
        return "Invoice{" +
                "code=" + CodeFacture +
                ", clientName='" + nomClient + '\'' +
                ", amount=" + MontantVersée +
                '}';
    }
	
	
}
