package br.com.datarey.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.generic.dao.GenericDAOImpl;

public class BaseDaoImpl<T> extends GenericDAOImpl<T> implements BaseDao<T> {

	@Override
	@Inject
	public void setEntityManager(EntityManager manager) {
		super.setEntityManager(manager);
	}
}
