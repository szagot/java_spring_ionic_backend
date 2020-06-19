package com.zefuinha.spring_ionic_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zefuinha.spring_ionic_backend.domain.Cidade;
import com.zefuinha.spring_ionic_backend.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;

	public List<Cidade> findByEstado(Integer estado_id) {
		return repository.findCidades(estado_id);
	}
}
