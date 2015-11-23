package br.com.datarey.service;

import java.util.List;

import javax.inject.Inject;

import br.com.datarey.dao.BaseDao;
import br.com.datarey.model.Entidade;
import br.com.datarey.transactional.Transactional;
import br.com.generic.dao.SearchEntityListBuilder;

@SuppressWarnings("rawtypes")
@Transactional
public abstract class BaseServiceImpl<E extends Entidade, D extends BaseDao> implements BaseService<E> {

    private static final long serialVersionUID = -7061695914391018105L;
    
    @Inject
    protected D dao;

    @SuppressWarnings("unchecked")
    public List<E> pesquisar(List<ItemPesquisa> itens){
        SearchEntityListBuilder<E> builder = dao.listEntities();
        
        for (ItemPesquisa itemPesquisa : itens) {
            itemPesquisa.getRegra().addSearch(itemPesquisa, builder);
        }
        
        return builder.list();
    }
}
