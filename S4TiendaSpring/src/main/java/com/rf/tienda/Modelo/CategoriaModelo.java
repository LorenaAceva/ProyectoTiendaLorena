package com.rf.tienda.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Transient;

public interface CategoriaModelo {
	
	
	
	@Transient //Puede ser que no nos grabe en la BBDD por estar activa esta anotación
	@JsonIgnore
	public boolean isValidInsert();
	@Transient
	@JsonIgnore
	public boolean isValidUpdate();

}
