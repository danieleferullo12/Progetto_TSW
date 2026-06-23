package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class OrdineBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idOrdine;
	private Date dataOrdine;
	private String stato;
	private double totale;
	private int idUtente;
	private List<DettaglioOrdineBean> dettagli;
	
	public OrdineBean(){		
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public Date getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public List<DettaglioOrdineBean> getDettagli(){
		
		return dettagli;
	}
	
	public void setDettagli(List<DettaglioOrdineBean> dettagli) {
		
		this.dettagli=dettagli;
		
	}
	
	
}
