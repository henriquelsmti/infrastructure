package br.com.datarey.model;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.NaturalId;

public abstract class EntidadeCodigo extends Entidade {

    @NaturalId
    private Long codigo;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).append(getCodigo()).toHashCode();
    }

}
