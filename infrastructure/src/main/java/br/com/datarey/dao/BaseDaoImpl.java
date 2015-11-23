package br.com.datarey.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.datarey.model.Entidade;
import br.com.generic.dao.GenericDAOImpl;

public class BaseDaoImpl<T extends Entidade> extends GenericDAOImpl<T> implements BaseDao<T> {

    @Inject
    private JpaUtil jpaUtil;
    
    @Override
    protected EntityManager getEntityManager() {
        return jpaUtil.getEntityManager();
    }
}
