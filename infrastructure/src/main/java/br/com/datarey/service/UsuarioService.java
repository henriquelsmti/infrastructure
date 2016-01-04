package br.com.datarey.service;

import java.util.List;

import br.com.datarey.model.Usuario;

public interface UsuarioService extends IdentificadorService<Usuario> {

    public List<Usuario> listarUsuariosPorNome(String nome);
}
