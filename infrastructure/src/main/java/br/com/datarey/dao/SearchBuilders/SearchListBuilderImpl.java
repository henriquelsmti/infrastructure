package br.com.datarey.dao.SearchBuilders;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.datarey.context.Context;
import br.com.datarey.dao.JpaUtil;

public class SearchListBuilderImpl<T, Q> extends br.com.generic.dao.SearchListBuilderImpl<T, Q> {

	public SearchListBuilderImpl(EntityManager manager, Class<T> fromClass,
			Class<Q> queryClass) {
		super(manager, fromClass, queryClass);
	}
	
	@Override
	public List<Q> list() {
	    boolean close = false;
	        JpaUtil jpaUtil = Context.getBean(JpaUtil.class);
	        if(jpaUtil.getEntityManager() == null){
	            setManager(jpaUtil.createEntityManager());
	            close = true;
	        }
	        List<Q> q = super.list();
	        if(close){
	            jpaUtil.destroyEntityManager();
	        }
	        return q;
	}

}
