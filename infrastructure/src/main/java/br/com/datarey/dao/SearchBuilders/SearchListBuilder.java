package br.com.datarey.dao.SearchBuilders;

import javax.persistence.EntityManager;

public class SearchListBuilder<T, Q> extends br.com.generic.dao.SearchListBuilder<T, Q> {

	public SearchListBuilder(EntityManager manager, Class<T> fromClass,
			Class<Q> queryClass) {
		super(manager, fromClass, queryClass);
	}

}
