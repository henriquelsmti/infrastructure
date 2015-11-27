package br.com.datarey.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<E> extends  Serializable{
    
    public E insert(E entity);
    
    public E save(E entity);
    
    public E delete(E entity);

    public E update(E entity);
    
    public E inactivate(E entity);

    public E findEntityById(long id);

    public List<E> list(int beginning, int end, String order);
    
    public List<E> list(List<ItemPesquisa> itens);
}
