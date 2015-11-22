package br.com.datarey.service;

import java.util.List;

public interface BaseService<E> {
    public List<E> pesquisar(List<ItemPesquisa> itens);
}
