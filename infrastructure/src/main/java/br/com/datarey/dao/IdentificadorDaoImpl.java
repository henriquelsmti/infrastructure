package br.com.datarey.dao;

import br.com.datarey.model.Identificador;

public class IdentificadorDaoImpl<E extends Identificador> extends BaseDaoImpl<E> implements IdentificadorDao<E> {

    @Override
    protected E beforeInsert(E entity) {
        Long codigo = max("codigo");
        entity.setCodigo(codigo == null || codigo == 0 ? 1 : codigo + 1);
        return super.beforeInsert(entity);
    }
}
