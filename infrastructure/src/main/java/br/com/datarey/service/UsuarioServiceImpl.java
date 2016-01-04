package br.com.datarey.service;

import java.util.List;

import br.com.datarey.dao.UsuarioDao;
import br.com.datarey.model.Usuario;

public class UsuarioServiceImpl extends IdentificadorServiceImpl<Usuario, UsuarioDao> implements
        UsuarioService {

 
    private static final long serialVersionUID = -3288343179754225619L;

    @Override
    public List<Usuario> listarUsuariosPorNome(String nome) {
        return dao.listEntities().like("nome", nome).list();
    }

}
