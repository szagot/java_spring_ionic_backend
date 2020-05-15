package com.zefuinha.spring_ionic_backend.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zefuinha.spring_ionic_backend.domain.Categoria;
import com.zefuinha.spring_ionic_backend.services.CategoriaService;

/**
 * Recursos para as Categorias
 * 
 * GET /categorias GET /categorias/{id}
 * 
 */

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	/**
	 * GET /categorias
	 *
	 * Listagem de teste
	 * 
	 * @return
	 */
	@GetMapping
	public List<Categoria> get() {
		// TODO: Mudar depois
		Categoria cat1 = new Categoria(1, "Informática");
		Categoria cat2 = new Categoria(2, "Escritório");
		Categoria cat3 = new Categoria();

		List<Categoria> categorias = new ArrayList<>();
		categorias.addAll(Arrays.asList(cat1, cat2));

		return categorias;
	}

	/**
	 * GET /categorias/{id}
	 *
	 * Listagem de teste
	 * 
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable Integer id) {

		Categoria categoria = service.findById(id);

		return ResponseEntity.ok().body(categoria);
		
	}

}
