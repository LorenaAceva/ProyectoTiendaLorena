package com.rf.tienda.Controller;

import java.util.List;


import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rf.tienda.Entity.Categoria;
import com.rf.tienda.Services.CategoriaService;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins="*")//Conexión con el frontend
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	/**
	 * Buscar por Id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public String[] buscarId(@PathVariable int id) {
		try {
			Categoria categoria = categoriaService.buscarId(id);
			return new String[] { "200", "Registro encontrado", categoria.toString() };
		} catch (NoSuchElementException e) {
			return new String[] { "400", "No existe registro solicitado" };
		}

	}

	/**
	 * GET Solicitar listas de Categoria
	 * 
	 * @return
	 */
	@GetMapping()
	public List<Categoria> listar() {
		return categoriaService.listar();
	}

	/**
	 * POST Registro nueva categoria
	 * 
	 * Usamos el método "isValid" para verificar que no se envien espacios vacios
	 * 
	 * @param c
	 * @return
	 */
	@PostMapping("/registro")
	public String[] nuevaCategoria(@RequestBody Categoria c) {
		c.setId_categoria(0);
		if (c.isValid()) {
			categoriaService.insertar(c);
			return new String[] { "200", "Registro Salvado" };
		} else {
			return new String[] { "500", "Regitro NO válido" };
		}
	}

	/**
	 * Método para actualizar las categorias
	 * 
	 * @param c
	 * @return
	 */
	@PutMapping
	public String[] actualizar(@RequestBody Categoria c) {
		categoriaService.modificar(c);
		return new String[] { "200", "Registro actualizado" };
	}

	/**
	 * DELETE Eliminar categoria por "id"
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public String[] eliminarId(@PathVariable("id") Integer id) {
		categoriaService.borrar(id);
		return new String[] { "200", "Registro borrado" };
	}

	@DeleteMapping
	public void eliminar(Categoria c) {
		eliminarId(c.getId_categoria());
	}

}
