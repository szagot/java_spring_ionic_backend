package com.zefuinha.spring_ionic_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.zefuinha.spring_ionic_backend.domain.Categoria;
import com.zefuinha.spring_ionic_backend.domain.Produto;
import com.zefuinha.spring_ionic_backend.repositories.CategoriaRepository;
import com.zefuinha.spring_ionic_backend.repositories.ProdutoRepository;
import com.zefuinha.spring_ionic_backend.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Produto> findAll() {
		return repository.findAll();
	}

	public Produto findById(Integer id) {

		// O Optional evita que ocorra um NullException caso não seja encontrada a
		// produto do id informado
		Optional<Produto> produto = repository.findById(id);

		// Retorna a produto, ou então uma exceção
		return produto.orElseThrow(() -> new ObjectNotFoundException(
				"Produto não encontrado. Id: " + id + ", Nome: " + Produto.class.getName()));

	}

	/**
	 * Busca todos os produtos pertencentes às categorias de IDs informados que
	 * possuam o texto de busca em seus nomes
	 * 
	 * @param busca
	 * @param catIds
	 * @param page
	 * @param limit
	 * @param orderBy
	 * @param direction
	 * @return
	 */
	public Page<Produto> search(String busca, List<Integer> catIds, Integer page, Integer limit, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, limit, Direction.valueOf(direction), orderBy);

		// Pega as categorias da lista de Ids informados
		List<Categoria> categorias = categoriaRepository.findAllById(catIds);

		return repository.search(busca, categorias, pageRequest);
	}

}
