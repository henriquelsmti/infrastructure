package br.com.datarey.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<E> extends  Serializable{
    public List<E> pesquisar(List<ItemPesquisa> itens);
}
