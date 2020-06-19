package com.zefuinha.spring_ionic_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zefuinha.spring_ionic_backend.domain.Estado;
import com.zefuinha.spring_ionic_backend.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;

	public List<Estado> findAll() {
		return repository.findAllByOrderByNome();
	}
}
