package model;

import java.io.Serializable;


public class DettaglioOrdineBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int idDettaglio;
	private int idOrdine;
	private int idProdotto;
	private String nomeProdotto;
	private int quantita;
	private double prezzoUnitario;
	
	
	public DettaglioOrdineBean() {
	}


	public int getIdDettaglio() {
		return idDettaglio;
	}


	public void setIdDettaglio(int idDettaglio) {
		this.idDettaglio = idDettaglio;
	}


	public int getIdOrdine() {
		return idOrdine;
	}


	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}


	public int getIdProdotto() {
		return idProdotto;
	}


	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	
	public void setNomeProdotto(String nomeProdotto) {
		
		this.nomeProdotto=nomeProdotto;
	}

	public String getNomeProdotto() {
		
		return nomeProdotto;
	}

	public int getQuantita() {
		return quantita;
	}


	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}


	public double getPrezzoUnitario() {
		return prezzoUnitario;
	}


	public void setPrezzoUnitario(double prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
