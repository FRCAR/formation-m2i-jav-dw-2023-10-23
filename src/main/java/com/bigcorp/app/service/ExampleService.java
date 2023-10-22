package com.bigcorp.app.service;

import com.bigcorp.app.dao.ExampleDao;
import com.bigcorp.app.model.Example;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Stateless
public class ExampleService {

	@Inject
	private ExampleDao exampleDao;

	@TransactionAttribute
	public Example save(Example example) {
		return this.exampleDao.merge(example);
	}
	
	@Transactional
	public void removeById(Long id) {
		this.exampleDao.remove(id);
	}

	public Example findById(Long id) {
		return this.exampleDao.findById(id);
	}

}