package com.zefuinha.spring_ionic_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.zefuinha.spring_ionic_backend.domain.Categoria;
import com.zefuinha.spring_ionic_backend.repositories.CategoriaRepository;
import com.zefuinha.spring_ionic_backend.services.exceptions.DataIntegrityException;
import com.zefuinha.spring_ionic_backend.services.exceptions.ObjectNotFoundException;

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

		// Retorna a categoria, ou então uma exceção
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Categoria não encontrada. Id: " + id + ", Tipo: " + Categoria.class.getName()));

	}

	public Categoria insert(Categoria categoria) {
		// Garante que o objeto inserido seja novo, sem ID
		categoria.setId(null);

		return repository.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		// Garante que o ID exista. Se não existir, o método já emite a exceção
		findById(categoria.getId());

		return repository.save(categoria);
	}

	public void delete(Integer id) {
		// Garante que o ID exista. Se não existir, o método já emite a exceção
		Categoria categoria = findById(id);

		try {
			repository.delete(categoria);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possua produtos.");
		}
	}

}
