package com.bigcorp.app.correction.dao;

import java.util.List;

import com.bigcorp.app.correction.model.RestaurantType;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class RestaurantTypeCorrectionDao {

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

	public List<RestaurantType> findWithRestaurants(Long id) {
		TypedQuery<RestaurantType> query = this.entityManager.createQuery(
				"select rt from RestaurantType rt  " +
						" left join fetch rt.restaurants restaurant "
						+ " where rt.id = :id", RestaurantType.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

}
