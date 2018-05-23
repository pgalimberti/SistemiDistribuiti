package Serializzazione;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {
	public static final long serialversionUID = 1;
	private String nome;
	private String cognome;
	private int eta;
	
	public Person(String nome, String cognome, int eta) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
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
	public int getEta() {
		return eta;
	}
	public void setEta(int eta) {
		this.eta = eta;
	}
}
