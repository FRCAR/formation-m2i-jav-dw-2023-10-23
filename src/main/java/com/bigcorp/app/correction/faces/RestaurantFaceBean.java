package com.bigcorp.app.correction.faces;

import java.io.Serializable;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class RestaurantFaceBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message = "Bienvenue au restaurant";
	private String loadId;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String createNew() {
		System.out.println("Création d'un restaurant");
		return "new-restaurant";
	}
	
	public void onLoad() {
		System.out.println("Je charge le restaurant avec l'id : " + this.loadId);
	}

	public String getLoadId() {
		return loadId;
	}

	public void setLoadId(String loadId) {
		this.loadId = loadId;
	}

}