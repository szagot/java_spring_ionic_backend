package com.zefuinha.spring_ionic_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zefuinha.spring_ionic_backend.domain.Pedido;
import com.zefuinha.spring_ionic_backend.repositories.PedidoRepository;
import com.zefuinha.spring_ionic_backend.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public List<Pedido> findAll() {
		return repository.findAll();
	}

	public Pedido findById(Integer id) {

		// O Optional evita que ocorra um NullException caso não seja encontrada a
		// pedido do id informado
		Optional<Pedido> pedido = repository.findById(id);

		// Retorna a pedido, ou então uma exceção
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Pedido não encontrado. Id: " + id + ", Tipo: " + Pedido.class.getName()));

	}

}
