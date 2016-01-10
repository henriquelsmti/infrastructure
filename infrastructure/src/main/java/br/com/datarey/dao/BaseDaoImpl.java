package br.com.datarey.dao;

import java.lang.reflect.ParameterizedType;

import javax.inject.Inject;
import javax.persistence.EntityManager;










import br.com.datarey.dao.SearchBuilders.SearchBuilderImpl;
import br.com.datarey.dao.SearchBuilders.SearchEntityBuilderImpl;
import br.com.datarey.dao.SearchBuilders.SearchEntityListBuilderImpl;
import br.com.datarey.dao.SearchBuilders.SearchListBuilderImpl;
import br.com.datarey.model.Entidade;
import br.com.datarey.model.type.EntidadeEstado;
import br.com.generic.dao.GenericDAOImpl;
import br.com.generic.dao.SearchBuilder;
import br.com.generic.dao.SearchEntityBuilder;
import br.com.generic.dao.SearchEntityListBuilder;
import br.com.generic.dao.SearchListBuilder;

public abstract class BaseDaoImpl<T extends Entidade> extends GenericDAOImpl<T> implements BaseDao<T> {

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

    public T activate(T entity) {
        entity = beforeActivate(entity);
        entity.setEstado(EntidadeEstado.ATIVO);
        entity = update(entity);
        return afterActivate(entity);
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
        if (entity.getId() == 0 || entity.getId() == null) {
            entity = insert(entity);
        } else {
            entity = update(entity);
        }
        return afterDelete(entity);
    }

    @Override
    public SearchEntityListBuilder<T> listEntities() {
        return new SearchEntityListBuilderImpl<T>(getEntityManager(), getEntityClass());
    }

    @Override
    public T findEntityById(long id) {
        return (T) getEntityManager().find(getEntityClass(), id);
    }

    @Override
    public SearchEntityBuilder<T> searchEntity() {
        return new SearchEntityBuilderImpl<T>(getEntityManager(), getEntityClass());
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> SearchListBuilder<T, E> listProperties(String field) {
        ParameterizedType paramType;
        paramType = (ParameterizedType) new Param<E>().getClass().getGenericInterfaces()[0];
        Class<E> parameterClass = (Class<E>) paramType.getActualTypeArguments()[0].getClass();

        SearchListBuilder<T, E> searchListBuilder = new SearchListBuilderImpl<T, E>(getEntityManager(), getEntityClass(),
                parameterClass);
        searchListBuilder.setField(field);
        return searchListBuilder;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> SearchBuilder<T, E> searchProperty(String field) {
        ParameterizedType paramType;
        paramType = (ParameterizedType) new Param<E>().getClass().getGenericInterfaces()[0];
        Class<E> parameterClass = (Class<E>) paramType.getActualTypeArguments()[0].getClass();

        SearchBuilder<T, E> searchBuilder = new SearchBuilderImpl<>(getEntityManager(), getEntityClass(), parameterClass);
        searchBuilder.setField(field);
        return searchBuilder;
    }

    protected T beforeInactivate(T entity) {
        return entity;
    }

    protected T afterInactivate(T entity) {
        return entity;
    }

    protected T beforeActivate(T entity) {
        return entity;
    }

    protected T afterActivate(T entity) {
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
