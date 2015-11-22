package br.com.datarey.dao;

import br.com.datarey.model.Entidade;
import br.com.generic.dao.GenericDAO;

public interface BaseDao<T extends Entidade> extends GenericDAO<T> {

}
