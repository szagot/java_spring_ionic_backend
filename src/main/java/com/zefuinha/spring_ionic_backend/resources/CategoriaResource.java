package com.zefuinha.spring_ionic_backend.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zefuinha.spring_ionic_backend.domain.Categoria;

/**
 * Recursos para as Categorias
 * 
 * GET /categorias
 * 
 */

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	/**
	 * GET /categorias
	 *
	 * Listagem de teste
	 * 
	 * @return
	 */
	@GetMapping
	public List<Categoria> listar() {
		Categoria cat1 = new Categoria(1, "Informática");
		Categoria cat2 = new Categoria(2, "Escritório");

		List<Categoria> categorias = new ArrayList<>();
		categorias.addAll(Arrays.asList(cat1, cat2));

		return categorias;
	}

}
