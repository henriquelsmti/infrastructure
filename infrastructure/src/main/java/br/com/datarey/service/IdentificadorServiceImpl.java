package br.com.datarey.service;

import br.com.datarey.dao.IdentificadorDao;
import br.com.datarey.model.Identificador;

public abstract class IdentificadorServiceImpl<E extends Identificador, D extends IdentificadorDao<E>> extends BaseServiceImpl<E, D>
        implements IdentificadorService<E> {
    private static final long serialVersionUID = -5289909198294954665L;
    
    @Override
    public E obterPorCodigo(long codigo) {
        return ((E) dao.searchEntity().eq("codigo", codigo).search());
    }
}
