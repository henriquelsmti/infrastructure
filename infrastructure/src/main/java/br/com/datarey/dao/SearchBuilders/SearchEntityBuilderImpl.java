package br.com.datarey.dao.SearchBuilders;

import javax.persistence.EntityManager;

import br.com.datarey.context.Context;
import br.com.datarey.dao.JpaUtil;
import br.com.generic.dao.SearchEntityBuilder;

public class SearchEntityBuilderImpl<T> extends br.com.generic.dao.SearchBuilderImpl<T, T> implements SearchEntityBuilder<T>{

    public SearchEntityBuilderImpl(EntityManager manager, Class<T> fromClass) {
        super(manager, fromClass, fromClass);
    }

    @Override
    public T search() {
        boolean close = false;
        JpaUtil jpaUtil = Context.getBean(JpaUtil.class);
        if(jpaUtil.getEntityManager() == null){
            setManager(jpaUtil.createEntityManager());
            close = true;
        }
        T q = super.search();
        if(close){
            jpaUtil.destroyEntityManager();
        }
        return q;
    }

}
