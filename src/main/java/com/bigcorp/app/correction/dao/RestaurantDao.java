package com.bigcorp.app.correction.dao;

import java.util.List;

import com.bigcorp.app.correction.model.Restaurant;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class RestaurantDao {

	@PersistenceContext
	protected EntityManager entityManager;

	public String getMessage() {
		return "je suis un DAO";
	}

	/**
	 * @see EntityManager#find(Class, Object)
	 * @param entity
	 */
	public Restaurant findById(Long id) {
		return this.entityManager.find(Restaurant.class, id);
	}

	/**
	 * @see EntityManager#merge(Object)
	 * @param entity
	 */
	public Restaurant merge(Restaurant entity) {
		return this.entityManager.merge(entity);
	}

	public void delete(Long id) {
		Restaurant restaurant = this.entityManager.find(Restaurant.class, id);
		this.entityManager.remove(restaurant);
	}

	public List<Restaurant> findByName(String argumentName) {
		TypedQuery<Restaurant> query = this.entityManager.createQuery(
				"select r from Restaurant r  "
						+ " where r.name = :jpqlName ", Restaurant.class);
		query.setParameter("jpqlName", argumentName);
		return query.getResultList();
	}

	public List<Restaurant> findByNameLikeCaseInsensitive(String name) {
		if(name == null) {
			name = "";
		}
		TypedQuery<Restaurant> query = this.entityManager.createQuery(
				"select r from Restaurant r  "
						+ " where upper(r.name) like :name ", Restaurant.class);
		query.setParameter("name", '%' + name.toUpperCase() + '%');
		return query.getResultList();
	}
	
	

}
