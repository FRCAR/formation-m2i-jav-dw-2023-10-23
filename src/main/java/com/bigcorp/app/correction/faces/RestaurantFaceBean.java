package com.bigcorp.app.correction.faces;

import java.io.Serializable;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class RestaurantFaceBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message = "Bienvenue au restaurant";
	private Long loadId;

	private RestaurantFormBean restaurantFormBean = new RestaurantFormBean();

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
		this.restaurantFormBean.setId(this.loadId);
		this.restaurantFormBean.setName("le restaurant avec l'id : " + this.loadId);
	}

	public Long getLoadId() {
		return loadId;
	}

	public void setLoadId(Long loadId) {
		this.loadId = loadId;
	}

	public RestaurantFormBean getRestaurantFormBean() {
		return restaurantFormBean;
	}

	public void setRestaurantFormBean(RestaurantFormBean restaurantFormBean) {
		this.restaurantFormBean = restaurantFormBean;
	}

	public String save() {
		System.out.println("Je sauvegarde le restaurantFormBean : " + this.restaurantFormBean);
		return "restaurant?faces-redirect=true&id=" + this.restaurantFormBean.getId();
	}

}
