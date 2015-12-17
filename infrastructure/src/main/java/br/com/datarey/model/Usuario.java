package br.com.datarey.model;

import javax.persistence.Entity;

import org.apache.commons.lang3.builder.EqualsBuilder;

@Entity
public class Usuario extends Identificador {

    private String login;
    private String senha;
    
    public String getLogin() {
        return this.login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return this.senha;
    }
    public void setSenha(String senha) {
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
