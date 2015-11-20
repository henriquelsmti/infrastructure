package br.com.datarey.service;

import javax.inject.Inject;

import br.com.datarey.dao.BaseDao;

public class BaseServiceImpl<E, D extends BaseDao<E>> implements BaseService<E> {

	@Inject
	protected D dao;
}
