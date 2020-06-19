package com.zefuinha.spring_ionic_backend.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zefuinha.spring_ionic_backend.domain.Cidade;
import com.zefuinha.spring_ionic_backend.domain.Estado;
import com.zefuinha.spring_ionic_backend.dto.CidadeDTO;
import com.zefuinha.spring_ionic_backend.dto.EstadoDTO;
import com.zefuinha.spring_ionic_backend.services.CidadeService;
import com.zefuinha.spring_ionic_backend.services.EstadoService;

/**
 * Recursos para as Estados
 * 
 * GET /estados GET /estados/{id}
 * 
 */

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired
	private EstadoService service;

	@Autowired
	private CidadeService cidadeService;
	
	/**
	 * GET /estados
	 */
	@GetMapping
	public ResponseEntity<List<EstadoDTO>> get() {
		List<Estado> estados = service.findAll();

		// Converte para DTO
		List<EstadoDTO> dto = estados.stream().map(x -> new EstadoDTO(x)).collect(Collectors.toList());

		return ResponseEntity.ok().body(dto);
	}
	
	/**
	 * GET /estados/{id}/cidades
	 */
	@GetMapping(value="/{id}/cidades")
	public ResponseEntity<List<CidadeDTO>> getCidades(@PathVariable Integer id) {
		List<Cidade> cidades = cidadeService.findByEstado(id);
		
		// Converte para DTO
		List<CidadeDTO> dto = cidades.stream().map(x -> new CidadeDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(dto);
	}

}
