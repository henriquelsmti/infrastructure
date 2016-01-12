package br.com.datarey.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE) 
public class Usuario extends Identificador {

    @NotBlank(message = "Login e obrigatorio")
    private String login;
    @NotBlank(message = "Senha e obrigatorio")
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
