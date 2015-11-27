package br.com.datarey.dao;

import br.com.datarey.model.Entidade;
import br.com.generic.dao.GenericDAO;

public interface BaseDao<T extends Entidade> extends GenericDAO<T> {
    public T inactivate(T entity);
    
    public T save(T entity);
}
