package br.com.datarey.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Identificador extends CodigoIdentificador {
    
    private String nome;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
