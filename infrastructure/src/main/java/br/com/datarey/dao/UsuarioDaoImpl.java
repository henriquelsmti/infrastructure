package br.com.datarey.dao;

import br.com.datarey.model.Usuario;

public class UsuarioDaoImpl extends IdentificadorDaoImpl<Usuario> implements UsuarioDao {

    @Override
    protected Usuario beforeInsert(Usuario entity) {
        return super.beforeInsert(entity);
    }
}
