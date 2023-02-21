package com.rf.tienda.Controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.rf.tienda.Services.GenericoServiceI;
import com.rf.tienda.exception.ControllerException;
import com.rf.tienda.exception.DomainException;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins="*")//Conexión con el frontend
public class CategoriaController {

	@Autowired
	private GenericoServiceI<Categoria,Integer> gService;

	/**
	 * Buscar por Id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public String[] buscarId(@PathVariable int id) {
		try {
			Categoria categoria = gService.buscarID(id);
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
	/*public List<Categoria> listar() {
		return gService.listar();
	}*/
	public ResponseEntity<Map<String, Object>> todaLista()throws ControllerException{
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Categoria> cat = gService.listar();
		if (!cat.isEmpty()) {
			map.put("status", 1);
			map.put("data", cat);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			throw new ControllerException("No existen datos");
		}
		
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
	public String[] nuevaCategoria(@RequestBody Categoria categoria) {
	    try {
	        categoria.setId_categoria(0);
	        if (categoria.isValid()) {
	            gService.insertar(categoria);
	            return new String[] { "200", "Registro Salvado" };
	        } else {
	            return new String[] { "500", "Regitro NO válido" };
	        }
	    } catch (Exception e) {
	        return new String[] { "500", "Error al guardar el registro: " + e.getMessage() };
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
	    try {
	        gService.modificar(c);
	        return new String[] { "200", "Registro actualizado" };
	    } catch (Exception e) {
	        return new String[] { "500", "Error al actualizar el registro: " + e.getMessage() };
	    }
	}


	/**
	 * DELETE Eliminar categoria por "id"
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public String[] eliminarId(@PathVariable("id") Integer id) {
	    try {
	        gService.borrarById(id);
	        return new String[] { "200", "Registro borrado" };
	    } catch (Exception e) {
	        return new String[] { "500", "Error al eliminar el registro: " + e.getMessage() };
	    }
	}


	@DeleteMapping
	public void eliminar(Categoria c) {
	    try {
	        eliminarId(c.getId_categoria());
	    } catch (Exception e) {
	        
	    }
	}


}
