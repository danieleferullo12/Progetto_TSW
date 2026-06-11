package model;

import java.io.Serializable;


public class ProdottoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
    
	private int idProdotto;
	private String nome;
	private String descrizione;
	private double prezzo;
	private int quantitaDisp;
	private String immagineUrl;
	private int idCategoria;
	
	public ProdottoBean() {
	}

	public int getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getQuantitaDisp() {
		return quantitaDisp;
	}

	public void setQuantitaDisp(int quantitàDisp) {
		this.quantitaDisp = quantitàDisp;
	}

	public String getImmagineUrl() {
		return immagineUrl;
	}

	public void setImmagineUrl(String immagine_url) {
		this.immagineUrl = immagine_url;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
