package com.bigcorp.app.correction.service;

import com.bigcorp.app.correction.dao.RestaurantCorrectionDao;
import com.bigcorp.app.correction.model.Restaurant;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class RestaurantCorrectionService {

	@Inject
	private RestaurantCorrectionDao restaurantDao;

	public String getMessage() {
		return "Message de restaurantDao";
	}

	public RestaurantCorrectionDao getRestaurantDao() {
		return restaurantDao;
	}

	public Restaurant findById(Long id) {
		return this.restaurantDao.findById(id);
	}

	public Restaurant save(Restaurant restaurant) {
		return this.restaurantDao.merge(restaurant);
	}

	public void delete(Long id) {
		this.restaurantDao.delete(id);
	}

}
