package com.rf.tienda.Repository;

/**
 * Interfaz que extiende del repositorio de JPA y que utilizará la clase CategoriaServicie
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.rf.tienda.Entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
