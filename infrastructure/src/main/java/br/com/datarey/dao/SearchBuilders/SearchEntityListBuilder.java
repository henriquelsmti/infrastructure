package br.com.datarey.dao.SearchBuilders;

import javax.persistence.EntityManager;

public class SearchEntityListBuilder<T> extends br.com.generic.dao.SearchEntityListBuilder<T> {

    public SearchEntityListBuilder(EntityManager manager, Class<T> fromClass) {
        super(manager, fromClass);
    }

	

}
