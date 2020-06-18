package com.zefuinha.spring_ionic_backend.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

	@Autowired
	private CNService cnService;

	@Value("${image.product.small.size}")
	private String smallSize;

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
	public Page<Produto> search(String busca, List<Integer> catIds, Integer page, Integer limit, String orderBy,
			String direction) {
		PageRequest pageRequest = PageRequest.of(page, limit, Direction.valueOf(direction), orderBy);

		// Pega as categorias da lista de Ids informados
		List<Categoria> categorias = categoriaRepository.findAllById(catIds);

		return repository.findDistinctByNomeContainingIgnoreCaseAndCategoriasIn(busca, categorias, pageRequest);
	}

	/**
	 * Faz o upload de uma foto de produto e salva
	 * 
	 * @param multipartFile
	 * @param id
	 * @param isSmall
	 * @return
	 */
	private URI uploadPicture(MultipartFile multipartFile, Integer id, boolean isSmall) {
		// Pega a categoria informada
		Produto produto = findById(id);

		// O profileSize está vazio ou não é miniatura?
		if (smallSize == null || smallSize.isEmpty() || !isSmall) {
			smallSize = "0";
		}

		// Faz o upload da imagem
		URI uri = cnService.uploadFile(multipartFile, Integer.parseInt(smallSize));

		// Salva a imagem no produto
		if (isSmall)
			produto.setImageSmallUrl(uri.toString());
		else
			produto.setImageUrl(uri.toString());

		repository.save(produto);

		// Devolve a uri da imagem
		return uri;
	}

	/**
	 * Faz o upload de uma foto normal de produto e salva
	 * 
	 * @param multipartFile
	 * @param id
	 * @param isSmall
	 * @return
	 */
	public URI uploadPicture(MultipartFile multipartFile, Integer id) {
		return uploadPicture(multipartFile, id, false);
	}

	/**
	 * Faz o upload de uma foto miniatura de produto e salva
	 * 
	 * @param multipartFile
	 * @param id
	 * @param isSmall
	 * @return
	 */
	public URI uploadSmallPicture(MultipartFile multipartFile, Integer id) {
		return uploadPicture(multipartFile, id, true);
	}
}
