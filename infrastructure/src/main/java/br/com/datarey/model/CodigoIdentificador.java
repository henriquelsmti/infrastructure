package br.com.datarey.model;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.NaturalId;

@MappedSuperclass
public abstract class CodigoIdentificador extends Entidade {

    @NaturalId
    @NotNull(message = "Codigo é obrigatorio")
    @Min(value = 0, message = "Codigo é obrigatorio")
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
