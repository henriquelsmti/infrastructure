package br.com.datarey.dao;

import br.com.datarey.model.Identificador;

public class IdentificadorDaoImpl<E extends Identificador> extends BaseDaoImpl<E> implements IdentificadorDao<E> {

    @Override
    protected E beforeInsert(E entity) {

        return super.beforeInsert(entity);
    }
}
