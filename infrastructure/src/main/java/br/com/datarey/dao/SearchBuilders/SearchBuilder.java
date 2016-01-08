package br.com.datarey.dao.SearchBuilders;

import javax.persistence.EntityManager;


public class SearchBuilder<T, Q> extends br.com.generic.dao.SearchBuilder<T, Q>{

    public SearchBuilder(EntityManager manager, Class<T> fromClass, Class<Q> queryClass) {
        super(manager, fromClass, queryClass);
    }



}
