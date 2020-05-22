package com.zefuinha.spring_ionic_backend.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zefuinha.spring_ionic_backend.domain.Pedido;
import com.zefuinha.spring_ionic_backend.services.PedidoService;

/**
 * Recursos para as Pedidos
 * 
 * GET /pedidos GET /pedidos/{id}
 * 
 */

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;

	/**
	 * GET /pedidos
	 *
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<Pedido>> get() {
		List<Pedido> pedidos = service.findAll();

		return ResponseEntity.ok().body(pedidos);
	}

	/**
	 * GET /pedidos/{id}
	 *
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> getById(@PathVariable Integer id) {

		Pedido pedido = service.findById(id);

		return ResponseEntity.ok().body(pedido);

	}
	
	/**
	 * POST /pedidos
	 * 
	 * Adicionado o \@Valid para ativar as validações do DTO
	 */
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido pedido) {
		pedido = service.insert(pedido);

		// Gera a URI do recurso inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

}
