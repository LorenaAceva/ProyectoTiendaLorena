package com.rf.tienda.Services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.rf.tienda.Modelo.CategoriaModelo;

/**
 * Clase abstracta con los métodos genéricos que usaran varias entidades
 * 
 * @author lorena.acedo.vazquez
 *
 * @param <T>
 * @param <ID>
 */

@Service
public abstract class GenericoServicie<T extends CategoriaModelo,ID, R extends JpaRepository<T, ID>> implements GenericoServiceI<T,ID> {

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
		return (List<T>)cRepository.findAll();
	}

	/**
	 * Insertamos los datos
	 * 
	 * @param entity
	 * @return
	 */
	public boolean insertar(T entity) {
		if(entity.isValidInsert()) {
			cRepository.save(entity);
			return true;
		}else {
			
		return false;
		}
		
	}

	/**
	 * Modificamos datos
	 * 
	 * @param entity
	 * @return
	 */
	public boolean modificar(T entity) {
		if (entity.isValidUpdate()) {
			cRepository.save(entity);
			return true;
		}else {
		return false;
		}
	}

	/**
	 * Borramos por id
	 * 
	 * @param id
	 */
	public boolean borrarById(ID id) {
		cRepository.deleteById(id);
		return true;

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
