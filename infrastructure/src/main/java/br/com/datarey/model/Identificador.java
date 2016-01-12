package br.com.datarey.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Identificador extends CodigoIdentificador {

    @NotBlank(message = "Nome é obrigatorio")
    private String nome;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
