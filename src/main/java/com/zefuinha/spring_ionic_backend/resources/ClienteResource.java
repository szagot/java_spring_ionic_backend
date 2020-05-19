package com.zefuinha.spring_ionic_backend.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.zefuinha.spring_ionic_backend.domain.Cliente;
import com.zefuinha.spring_ionic_backend.dto.ClienteDTO;
import com.zefuinha.spring_ionic_backend.dto.ClienteNewDTO;
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
	 */
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> get() {
		List<Cliente> clientes = service.findAll();

		// Converte para DTO
		List<ClienteDTO> dto = clientes.stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList());

		return ResponseEntity.ok().body(dto);
	}

	/**
	 * GET /clientes/page?page=1&limit=2&orderBy=id&direction=DESC
	 */
	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClienteDTO>> getPerPage(// @formatter:off
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
		Page<Cliente> clientes = service.findPage(page, limit, orderBy, direction);

		// Converte para DTO
		Page<ClienteDTO> dto = clientes.map(x -> new ClienteDTO(x));

		return ResponseEntity.ok().body(dto);
	}

	/**
	 * GET /clientes/{id}
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> getById(@PathVariable Integer id) {
		Cliente cliente = service.findById(id);

		return ResponseEntity.ok().body(cliente);
	}

	/**
	 * POST /clientes
	 */
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO clienteDTO) {
		Cliente cliente = service.insert(service.fromDTO(clienteDTO));

		// Gera a URI do recurso inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	/**
	 * PUT /clientes/{id}
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO) {
		clienteDTO.setId(id);
		service.update(service.fromDTO(clienteDTO));

		return ResponseEntity.noContent().build();
	}

	/**
	 * DELETE /clientes/{id}
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

}
