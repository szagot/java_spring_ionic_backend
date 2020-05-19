package com.zefuinha.spring_ionic_backend.dto;

import java.io.Serializable;

import com.zefuinha.spring_ionic_backend.domain.Categoria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Util para definir quais dados voltar quando não quero que tragam todos
 * 
 * https://medium.com/@msealvial/blindando-sua-api-spring-boot-com-o-padr%C3%A3o-dto-44f97020d1a0
 */

@NoArgsConstructor
@Getter
@Setter
public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;

	public CategoriaDTO(Categoria categoria) {
		id = categoria.getId();
		nome = categoria.getNome();
	}
}
