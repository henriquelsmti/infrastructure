package br.com.datarey.model;

public abstract class Identificador extends CodigoIdentificador {
    
    private String nome;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
