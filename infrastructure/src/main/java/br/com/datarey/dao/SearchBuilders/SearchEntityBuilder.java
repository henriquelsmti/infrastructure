package br.com.datarey.dao.SearchBuilders;

import javax.persistence.EntityManager;

public class SearchEntityBuilder<T> extends br.com.generic.dao.SearchBuilder<T, T> {

    public SearchEntityBuilder(EntityManager manager, Class<T> fromClass) {
        super(manager, fromClass, fromClass);
    }

	

}
