package com.zefuinha.spring_ionic_backend.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zefuinha.spring_ionic_backend.domain.Categoria;
import com.zefuinha.spring_ionic_backend.dto.CategoriaDTO;
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
	 */
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> get() {
		List<Categoria> categorias = service.findAll();

		// Converte para DTO
		List<CategoriaDTO> dto = categorias.stream().map(x -> new CategoriaDTO(x)).collect(Collectors.toList());

		return ResponseEntity.ok().body(dto);
	}

	/**
	 * GET /categorias/page?page=1&limit=2&orderBy=id&direction=DESC
	 */
	@GetMapping(value = "/page")
	public ResponseEntity<Page<CategoriaDTO>> getPerPage(// @formatter:off
			@RequestParam(value = "page", defaultValue = "0")
			Integer page, 
			@RequestParam(value = "limit", defaultValue = "10")
			Integer limit, 
			@RequestParam(value = "orderBy", defaultValue = "nome")
			String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC")
			String direction
			 
			// @formatter:on
	) {
		Page<Categoria> categorias = service.findPage(page, limit, orderBy, direction);

		// Converte para DTO
		Page<CategoriaDTO> dto = categorias.map(x -> new CategoriaDTO(x));

		return ResponseEntity.ok().body(dto);
	}

	/**
	 * GET /categorias/{id}
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable Integer id) {
		Categoria categoria = service.findById(id);

		return ResponseEntity.ok().body(categoria);
	}

	/**
	 * POST /categorias
	 */
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Categoria categoria) {
		categoria = service.insert(categoria);

		// Gera a URI do recurso inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	/**
	 * PUT /categorias/{id}
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Categoria categoria) {
		categoria.setId(id);
		categoria = service.update(categoria);

		return ResponseEntity.noContent().build();
	}

	/**
	 * DELETE /categorias/{id}
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

}
