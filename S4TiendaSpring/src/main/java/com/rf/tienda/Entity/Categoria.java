package com.rf.tienda.Entity;

import java.io.Serializable;

import com.rf.tienda.Modelo.CategoriaModelo;
import com.rf.tienda.util.Validator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 
 * Nombre Categoria Descripcion Lista de categorías
 * 
 * @author Lorena Acedo
 * @version 8 de Febrero de 2023
 *
 */

@Entity
@Table(name = "CATEGORIA")
public class Categoria implements Serializable, CategoriaModelo {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CATEGORIA")
	private int id_categoria; // identificador categoria

	@Column(name = "CAT_NOMBRE")
	private String cat_nombre; // nombre de la categoria

	@Column(name = "CAT_DESCRIPCION")
	private String cat_descripcion; // descripcion de la categoria

	public Categoria() {
	}

	/**
	 * Este método evitará que haya un campo vacio
	 * @return
	 */
	public boolean isValid() {
		return !Validator.isVacio(cat_nombre) && id_categoria > 0;
	}

	/**
	 * Getter para identificador de categoria
	 * 
	 * @return Integer con el id de la categoria
	 */
	public int getId_categoria() {
		return id_categoria;
	}

	/**
	 * Setter para identificador de categoria
	 * 
	 */
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	/**
	 * Getter para el nombre de categoria
	 * 
	 * @return cadena con el nombre de la categoria
	 */
	public String getCat_nombre() {
		return cat_nombre;
	}

	/**
	 * Setter para el nombre de categoria
	 * 
	 * @throws Exception
	 * 
	 */
	public void setCat_nombre(String cat_nombre) {
		if (Validator.cumpleLongitud(cat_nombre,5,50)) {
			this.cat_nombre = cat_nombre;
		} else {
			System.out.println("El tamaño del nombre no es correcto");
		}

	}

	/**
	 * Getter para la descripcion de categoria
	 * 
	 * @return cadena con la descripcion de la categoria
	 */
	public String getCat_descripcion() {		
		return cat_descripcion;
	}

	/**
	 * Setter para la descripcion de categoria y su filtro de longitud máxima
	 * 
	 */
	public void setCat_descripcion(String cat_descripcion) {
		if (Validator.cumpleLongitudMax(cat_descripcion, 200)) {
			this.cat_descripcion = cat_descripcion;
		} else {
			System.out.println("El tamaño del nombre no es correcto");
		}
	}
	

	/**
	 * El método hashCode nos dará un identificador de cada objeto, que nos ayudará
	 * luego a compararlos
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cat_descripcion == null) ? 0 : cat_descripcion.hashCode());
		result = prime * result + ((cat_nombre == null) ? 0 : cat_nombre.hashCode());
		result = prime * result + id_categoria;
		return result;
	}

	/**
	 * Introducimos el método equal(obj)para comparar si dos objetos tienen los
	 * mismos datos
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (cat_descripcion == null) {
			if (other.cat_descripcion != null)
				return false;
		} else if (!cat_descripcion.equals(other.cat_descripcion))
			return false;
		if (cat_nombre == null) {
			if (other.cat_nombre != null)
				return false;
		} else if (!cat_nombre.equals(other.cat_nombre))
			return false;
		if (id_categoria != other.id_categoria)
			return false;
		return true;
	}

	/**
	 * Introducimos el método toString
	 */
	@Override
	public String toString() {
		return "Categoria [id_categoria=" + id_categoria + ", cat_nombre=" + cat_nombre + ", cat_descripcion="
				+ cat_descripcion + "]";
	}
	
	//@Override
	public boolean isValidInsert() {
		boolean result= !Validator.isVacio(cat_nombre);
		System.out.println(Validator.isVacio(cat_nombre));
		
		return result;
	}
	
	//@Override
	public boolean isValidUpdate() {
		boolean result=!Validator.isVacio(cat_nombre)&&
				id_categoria > 0;
		System.out.println(Validator.isVacio(cat_nombre));
		System.out.println(id_categoria>0);
		return result;
	}
	
	

}
