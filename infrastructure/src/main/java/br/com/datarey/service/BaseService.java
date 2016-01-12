package br.com.datarey.service;

import br.com.generic.dao.SearchBuilder;
import br.com.generic.dao.SearchEntityBuilder;
import br.com.generic.dao.SearchEntityListBuilder;
import br.com.generic.dao.SearchListBuilder;

import java.io.Serializable;
import java.util.List;

public interface BaseService<E> extends  Serializable{
    
    public E insert(E entity);
    
    public E save(E entity);
    
    public E delete(E entity);

    public E update(E entity);
    
    public E inactivate(E entity);

    public E activate(E entity);

    public E createModel();

    public E findEntityById(long id);

    public List<E> list(int beginning, int end, String order);
    
    public SearchEntityListBuilder<E> listEntities();

    public SearchEntityBuilder<E> searchEntity();

    public <T> SearchListBuilder<E, T> listProperties(String field);

    public<T> SearchBuilder<E, T> searchProperty(String field);
}
