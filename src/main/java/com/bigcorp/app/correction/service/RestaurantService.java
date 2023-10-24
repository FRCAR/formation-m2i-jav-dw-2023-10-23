package com.bigcorp.app.correction.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class RestaurantService {

	@Inject
	private RestaurantService restaurantDao;

	public RestaurantService getRestaurantDao() {
		return restaurantDao;
	}

	public void setRestaurantDao(RestaurantService restaurantDao) {
		this.restaurantDao = restaurantDao;
	}

	public String getMessage() {
		return "Message de restaurantDao";
	}

}
