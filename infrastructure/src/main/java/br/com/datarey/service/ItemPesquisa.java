package br.com.datarey.service;

import br.com.datarey.service.type.Regra;

public class ItemPesquisa {

    private String propriedade; 
    private Regra regra; 
    private Object value;
    
    public ItemPesquisa(){
        super();
    }
    
    public ItemPesquisa(String propriedade, Regra regra, Object value) {
        this.propriedade = propriedade;
        this.regra = regra;
        this.value = value;
    }

    public String getPropriedade() {
        return propriedade;
    }

    public void setPropriedade(String propriedade) {
        this.propriedade = propriedade;
    }

    public Regra getRegra() {
        return regra;
    }

    public void setRegra(Regra regra) {
        this.regra = regra;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
    

}
