package com.zefuinha.spring_ionic_backend.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
