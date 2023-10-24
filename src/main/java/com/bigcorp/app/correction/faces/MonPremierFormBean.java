package com.bigcorp.app.correction.faces;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class MonPremierFormBean implements Serializable {

	private String maValeur;

	public MonPremierFormBean() {
		System.out.println("Je suis MonPremierFormBean, et j'ai été instancié");
		this.maValeur = "Salut, on est le  : " + LocalDateTime.now(); 
	}

	public String getMaValeur() {
		return maValeur;
	}

	public void setMaValeur(String maValeur) {
		this.maValeur = maValeur;
	}

}