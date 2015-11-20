package br.com.datarey.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Entidade {
	
	@Id
	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
