package com.bigcorp.app.dao;

import com.bigcorp.app.model.Example;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ExampleDao {

	@PersistenceContext(unitName = "PersisterPU")
	protected EntityManager entityManager;

	public Example merge(Example object) {
		return this.entityManager.merge(object);
	}

	public Example findById(Long id) {
		return this.entityManager.find(Example.class, id);
	}

	public void remove(Long id) {
		this.entityManager.createQuery("delete from Example e where e.id = :id", Example.class).setParameter("id", id)
				.executeUpdate();
	}

}
