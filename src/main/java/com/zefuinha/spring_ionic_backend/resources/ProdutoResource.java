package com.zefuinha.spring_ionic_backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zefuinha.spring_ionic_backend.domain.Produto;
import com.zefuinha.spring_ionic_backend.dto.ProdutoDTO;
import com.zefuinha.spring_ionic_backend.resources.utils.URL;
import com.zefuinha.spring_ionic_backend.services.ProdutoService;

/**
 * Recursos para as Produtos
 * 
 * GET /produtos GET /produtos/{id}
 * 
 */

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	/**
	 * GET
	 * /produtos?busca=computador&categorias=1,3,4&page=1&limit=2&orderBy=id&direction=DESC
	 */
	// @formatter:off
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> getPerPage(
			@RequestParam(value = "busca", defaultValue = "")
			String busca, 
			@RequestParam(value = "categorias", defaultValue = "")
			String categorias, 
			@RequestParam(value = "page", defaultValue = "0")
			Integer page, 
			@RequestParam(value = "limit", defaultValue = "10")
			Integer limit, 
			@RequestParam(value = "orderBy", defaultValue = "nome")
			String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC")
			String direction
	) {
		// Busca o produto
		Page<Produto> produtos = service.search(
				URL.decodeParam(busca), 
				URL.decodeIntList(categorias), 
				page, limit, orderBy, direction
		);

		// Converte para DTO
		Page<ProdutoDTO> dto = produtos.map(x -> new ProdutoDTO(x));

		return ResponseEntity.ok().body(dto);
	}
	// @formatter:on

	/**
	 * GET /produtos/{id}
	 *
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Integer id) {

		Produto produto = service.findById(id);

		return ResponseEntity.ok().body(produto);

	}

}
