package model;

import java.io.Serializable;

public class UtenteBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int idUtente;
	private String nome;
	private String cognome;
	private String email;
	private String ruolo;
	private String passwordHash;
	private String indirizzoSpedizione;
	private String telefono;


  public UtenteBean() { 
  }


  public int getIdUtente() {
	return idUtente;
  }


  public void setIdUtente(int idUtente) {
	this.idUtente = idUtente;
  }


  public String getNome() {
	return nome;
  }


  public void setNome(String nome) {
	this.nome = nome;
  }


  public String getCognome() {
	return cognome;
  }


  public void setCognome(String cognome) {
	this.cognome = cognome;
  }


  public String getEmail() {
	return email;
  }


  public void setEmail(String email) {
	this.email = email;
  }


  public String getRuolo() {
	return ruolo;
  }


  public void setRuolo(String ruolo) {
	this.ruolo = ruolo;
  }


  public String getPasswordHash() {
	return passwordHash;
  }


  public void setPasswordHash(String passwordHash) {
	this.passwordHash = passwordHash;
  }


  public String getIndirizzoSpedizione() {
	return indirizzoSpedizione;
  }


  public void setIndirizzoSpedizione(String indirizzoSpedizione) {
	this.indirizzoSpedizione = indirizzoSpedizione;
  }


  public String getTelefono() {
	return telefono;
  }


  public void setTelefono(String telefono) {
	this.telefono = telefono;
  }
  
  
}

  