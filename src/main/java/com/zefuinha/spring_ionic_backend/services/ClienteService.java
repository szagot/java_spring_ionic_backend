package com.zefuinha.spring_ionic_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zefuinha.spring_ionic_backend.domain.Cliente;
import com.zefuinha.spring_ionic_backend.repositories.ClienteRepository;
import com.zefuinha.spring_ionic_backend.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente findById(Integer id) {

		// O Optional evita que ocorra um NullException caso não seja encontrada a
		// cliente do id informado
		Optional<Cliente> cliente = repository.findById(id);

		// Retorna a cliente, ou então uma exceção
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
			"Cliente não encontrado. Id: " + id + ", Tipo: " + Cliente.class.getName())
		);

	}

}
