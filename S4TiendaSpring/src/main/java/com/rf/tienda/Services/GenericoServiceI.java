package com.rf.tienda.Services;

import java.util.List;

public interface GenericoServiceI <T, ID> {
	
	public boolean insertar(T entity);
	public boolean modificar(T entity);
	public boolean borrarById(ID id);
	public List<T> listar();
	public T buscarID(ID id);

}
