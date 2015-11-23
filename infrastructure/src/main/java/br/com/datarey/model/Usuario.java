package br.com.datarey.model;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class Usuario extends NomeCodigo {

    private String login;
    private String senha;
    protected String getLogin() {
        return this.login;
    }
    protected void setLogin(String login) {
        this.login = login;
    }
    protected String getSenha() {
        return this.senha;
    }
    protected void setSenha(String senha) {
        this.senha = senha;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if(!(obj instanceof Usuario))
            return false;
        
        Usuario other = (Usuario) obj;
        return new EqualsBuilder().append(other.getLogin(), getLogin())
                .isEquals();
    }
}
