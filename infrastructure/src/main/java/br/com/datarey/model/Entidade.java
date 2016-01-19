package br.com.datarey.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.datarey.model.type.EntidadeEstado;

import java.io.Serializable;

@MappedSuperclass
public abstract class Entidade implements Serializable{

    @Id
    @GeneratedValue
    private Long id;
    
    private EntidadeEstado estado;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EntidadeEstado getEstado() {
        return this.estado;
    }

    public void setEstado(EntidadeEstado estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Entidade other = (Entidade) obj;
        return new EqualsBuilder().append(other.getId(), getId()).isEquals();
    }

}
