package br.com.datarey.service;

public interface IdentificadorService<E> extends  BaseService<E>{
    public E obterPorCodigo(long codigo);
}
