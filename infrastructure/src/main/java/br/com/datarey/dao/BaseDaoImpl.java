package br.com.datarey.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.datarey.model.Entidade;
import br.com.datarey.model.type.EntidadeEstado;
import br.com.generic.dao.GenericDAOImpl;

public class BaseDaoImpl<T extends Entidade> extends GenericDAOImpl<T> implements BaseDao<T> {

    @Inject
    private JpaUtil jpaUtil;
    
    @Override
    protected EntityManager getEntityManager() {
        return jpaUtil.getEntityManager();
    }
    
    public T inactivate(T entity) {
        entity = beforeInactivate(entity);
        entity.setEstado(EntidadeEstado.INATIVO);
        entity = update(entity);
        return afterInactivate(entity);
    }
    
    @Override
    public T insert(T entity) {
        entity = beforeSave(entity);
        entity = super.insert(entity);
        return afterSave(entity);
    }
    
    @Override
    public T update(T entity) {
        entity = beforeSave(entity);
        entity = super.update(entity);
        return afterSave(entity);
    }
    
    @Override
    public T save(T entity) {
        entity = beforeSave(entity);
        if(entity.getId() == 0 || entity.getId() == null){
            entity = insert(entity);
        }else{
            entity = update(entity);
        }
        return afterDelete(entity);
    }
    
    protected T beforeInactivate(T entity) {
        return entity;
    }

    protected T afterInactivate(T entity) {
        return entity;
    }
    
    protected T afterUpdate(T entity) {
        return entity;
    }
    
    protected T beforeSave(T entity) {
        return entity;
    }
    
    protected T afterSave(T entity) {
        return entity;
    }
}
