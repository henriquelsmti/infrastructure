package br.com.datarey.model;

public abstract class NomeCodigo extends EntidadeCodigo {
    
    private String nome;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
