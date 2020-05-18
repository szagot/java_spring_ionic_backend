package com.zefuinha.spring_ionic_backend.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zefuinha.spring_ionic_backend.domain.Cliente;
import com.zefuinha.spring_ionic_backend.services.ClienteService;

/**
 * Recursos para as Clientes
 * 
 * GET /clientes GET /clientes/{id}
 * 
 */

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	/**
	 * GET /clientes
	 *
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<Cliente>> get() {
		List<Cliente> clientes = service.findAll();

		return ResponseEntity.ok().body(clientes);
	}

	/**
	 * GET /clientes/{id}
	 *
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> getById(@PathVariable Integer id) {

		Cliente cliente = service.findById(id);

		return ResponseEntity.ok().body(cliente);

	}

}
