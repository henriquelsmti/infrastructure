package br.com.datarey.service;

import java.util.List;

import javax.inject.Inject;

import br.com.datarey.dao.BaseDao;
import br.com.datarey.model.Entidade;
import br.com.datarey.transactional.Transactional;
import br.com.generic.dao.SearchEntityListBuilder;

@Transactional
public class BaseServiceImpl<E extends Entidade, D extends BaseDao<E>> implements BaseService<E> {

    @Inject
    protected D dao;

    public List<E> pesquisar(List<ItemPesquisa> itens){
        SearchEntityListBuilder<E> builder = dao.listEntities();
        
        for (ItemPesquisa itemPesquisa : itens) {
            itemPesquisa.getRegra().addSearch(itemPesquisa, builder);
        }
        
        return builder.list();
    }
}
