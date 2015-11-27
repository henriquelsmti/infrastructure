package br.com.datarey.service;

import java.util.List;

import javax.inject.Inject;

import br.com.datarey.dao.BaseDao;
import br.com.datarey.model.Entidade;
import br.com.datarey.transactional.Transactional;
import br.com.generic.dao.SearchEntityListBuilder;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Transactional
public abstract class BaseServiceImpl<E extends Entidade, D extends BaseDao>
        implements BaseService<E> {

    private static final long serialVersionUID = -7061695914391018105L;

    @Inject
    protected D dao;

    @Override
    public E insert(E entity) {
        entity = beforeSave(entity);
        entity = beforeInsert(entity);
        entity = (E) dao.insert(entity);
        entity = afterInsert(entity);
        entity = afterSave(entity);
        return entity;
    }

    @Override
    public E save(E entity) {
        entity = beforeSave(entity);
        if(entity.getId() == 0 || entity.getId() == null){
            entity = beforeInsert(entity);
            entity = (E) dao.save(entity);
            entity = afterInsert(entity);
        }else{
            entity = beforeUpdate(entity);
            entity = update(entity);
            entity = afterUpdate(entity);
        }
        entity = afterSave(entity);
        return entity;
    }

    @Override
    public E delete(E entity) {
        entity = beforeDelete(entity);
        entity = (E) dao.insert(entity);
        entity = afterDelete(entity);
        return entity;
    }

    @Override
    public E update(E entity) {
        entity = beforeSave(entity);
        entity = beforeUpdate(entity);
        entity = (E) dao.update(entity);
        entity = afterUpdate(entity);
        entity = afterSave(entity);
        return entity;
    }
    
    @Override
    public E inactivate(E entity) {
        entity = beforeInactivate(entity);
        entity = (E) dao.inactivate(entity);
        entity = afterInactivate(entity);
        return entity;
    }

    @Override
    public E findEntityById(long id) {
        return (E) dao.findEntityById(id);
    }

    @Override
    public List<E> list(int beginning, int end, String order) {
        return (List<E>) dao.list(beginning, end, order);
    }

    public List<E> list(List<ItemPesquisa> itens) {
        SearchEntityListBuilder<E> builder = dao.listEntities();

        for (ItemPesquisa itemPesquisa : itens) {
            itemPesquisa.getRegra().addSearch(itemPesquisa, builder);
        }
        builder.sortBy(itens.get(0).getPropriedade());

        return builder.list();
    }

    protected E consist(E entity) {
        return entity;
    }

    protected E beforeInsert(E entity) {
        return entity;
    }

    protected E afterInsert(E entity) {
        return entity;
    }

    protected E beforeDelete(E entity) {
        return entity;
    }

    protected E afterDelete(E entity) {
        return entity;
    }

    protected E beforeUpdate(E entity) {
        return entity;
    }

    protected E afterUpdate(E entity) {
        return entity;
    }
    
    protected E beforeSave(E entity) {
        return entity;
    }

    protected E afterSave(E entity) {
        return entity;
    }
    
    protected E beforeInactivate(E entity) {
        return entity;
    }

    protected E afterInactivate(E entity) {
        return entity;
    }
    
    
}
