package br.com.datarey.dao.SearchBuilders;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.datarey.context.Context;
import br.com.datarey.dao.JpaUtil;

public class SearchEntityListBuilderImpl<T> extends br.com.generic.dao.SearchEntityListBuilderImpl<T> {

    public SearchEntityListBuilderImpl(EntityManager manager, Class<T> fromClass) {
        super(manager, fromClass);
    }

    @Override
    public List<T> list() {
        boolean close = false;
        JpaUtil jpaUtil = Context.getBean(JpaUtil.class);
        if(jpaUtil.getEntityManager() == null){
            setManager(jpaUtil.createEntityManager());
            close = true;
        }
        List<T> q = super.list();
        if(close){
            jpaUtil.destroyEntityManager();
        }
        return q;
    }

}
