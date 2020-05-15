package com.zefuinha.spring_ionic_backend.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public String listar() {
		return "Teste de recurso";
	}

}
