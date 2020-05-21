package com.zefuinha.spring_ionic_backend.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zefuinha.spring_ionic_backend.domain.Categoria;
import com.zefuinha.spring_ionic_backend.domain.Produto;

@Repository
@Transactional(readOnly=true)
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	/**
	 * \@Query:
	 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.at-query
	 * 
	 * Padrão:
	 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	 * 
	 * Query: 
	 * 
	 * SELECT DISTINCT 
	 * 		p 
	 * FROM 
	 * 		Produto p 
	 * INNER JOIN 
	 * 		p.categorias c 
	 * WHERE
	 * 		p.nome LIKE %:busca% AND c IN :categorias
	 */

	@Query("SELECT DISTINCT p FROM Produto p INNER JOIN p.categorias c WHERE p.nome LIKE %:busca% AND c IN :categorias")
	Page<Produto> search(
			@Param("busca") 
			String busca,
			
			@Param("categorias") 
			List<Categoria> categorias,
			
			Pageable pageRequest
	);

}
