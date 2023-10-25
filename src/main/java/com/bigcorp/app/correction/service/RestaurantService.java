package com.bigcorp.app.correction.service;

import com.bigcorp.app.correction.dao.RestaurantDao;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class RestaurantService {

	@Inject
	private RestaurantDao restaurantDao;

	public String getMessage() {
		return "Message de restaurantDao";
	}

	public RestaurantDao getRestaurantDao() {
		return restaurantDao;
	}

}
