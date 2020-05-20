package com.zefuinha.spring_ionic_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zefuinha.spring_ionic_backend.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	/**
	 * Busca pelo email, seguindo o padrão do framework de modo a não precisar criar
	 * a lógica
	 * 
	 * readOnly = true -> não necessita ser envlvida por uma transação do BD
	 * 
	 * @param email
	 * @return
	 */
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);

}
