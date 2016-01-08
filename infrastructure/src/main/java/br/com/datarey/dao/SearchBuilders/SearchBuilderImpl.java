package br.com.datarey.dao.SearchBuilders;

import javax.persistence.EntityManager;

import br.com.datarey.context.Context;
import br.com.datarey.dao.JpaUtil;


public class SearchBuilderImpl<T, Q> extends br.com.generic.dao.SearchBuilderImpl<T, Q>{

    
    public SearchBuilderImpl(EntityManager manager, Class<T> fromClass, Class<Q> queryClass) {
        super(manager, fromClass, queryClass);
    }

    @Override
    public Q search() {
        boolean close = false;
        JpaUtil jpaUtil = Context.getBean(JpaUtil.class);
        if(jpaUtil.getEntityManager() == null){
            setManager(jpaUtil.createEntityManager());
            close = true;
        }
        Q q = super.search();
        if(close){
            jpaUtil.destroyEntityManager();
        }
        return q;
    }

}
