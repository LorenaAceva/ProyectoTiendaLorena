package com.rf.tienda.Services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Clase abstracta con los métodos genéricos que usaran varias entidades
 * 
 * @author lorena.acedo.vazquez
 *
 * @param <T>
 * @param <ID>
 */

@Service
public abstract class GenericoServicie<T, ID> {

	/**
	 * Usa el JpaRepository para hacer los métodos
	 */
	@Autowired
	private JpaRepository<T, ID> cRepository;

	/**
	 * Devuelve todos los objetos almacenados en un repository
	 * 
	 * @return
	 */

	public List<T> listar() {
		return cRepository.findAll();
	}

	/**
	 * Insertamos los datos
	 * 
	 * @param entity
	 * @return
	 */
	public T insertar(T entity) {
		return cRepository.save(entity);
	}

	/**
	 * Modificamos datos
	 * 
	 * @param entity
	 * @return
	 */
	public T modificar(T entity) {
		return cRepository.save(entity);
	}

	/**
	 * Borramos por id
	 * 
	 * @param id
	 */
	public void borrar(ID id) {
		cRepository.deleteById(id);

	}

	/**
	 * Búsqueda de datos por Id
	 * 
	 * Nos lanzará una NoSuchElementException si no se encontrase dicho dato
	 * 
	 * @param id
	 * @return el dato o la excepción
	 */
	public T buscarId(ID id) {
		try {
			return (T) cRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}
}
