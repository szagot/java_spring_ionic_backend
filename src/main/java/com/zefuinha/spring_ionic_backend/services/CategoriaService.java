package com.zefuinha.spring_ionic_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zefuinha.spring_ionic_backend.domain.Categoria;
import com.zefuinha.spring_ionic_backend.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public List<Categoria> findAll() {
		return repository.findAll();
	}

	public Categoria findById(Integer id) {

		// O Optional evita que ocorra um NullException caso não seja encontrada a
		// categoria do id informado
		Optional<Categoria> categoria = repository.findById(id);

		// Retorna a categoria, ou então retorna null
		return categoria.orElse(null);

	}

}
