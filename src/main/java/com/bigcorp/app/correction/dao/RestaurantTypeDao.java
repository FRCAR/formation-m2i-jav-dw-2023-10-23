package com.bigcorp.app.correction.dao;

import com.bigcorp.app.correction.model.RestaurantType;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class RestaurantTypeDao {

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * @see EntityManager#find(Class, Object)
	 * @param entity
	 */
	public RestaurantType findById(Long id) {
		return this.entityManager.find(RestaurantType.class, id);
	}

	/**
	 * @see EntityManager#merge(Object)
	 * @param entity
	 */
	public RestaurantType merge(RestaurantType entity) {
		return this.entityManager.merge(entity);
	}

	public void delete(Long id) {
		RestaurantType restaurantType = this.entityManager.find(RestaurantType.class, id);
		this.entityManager.remove(restaurantType);
	}

	
	

}
