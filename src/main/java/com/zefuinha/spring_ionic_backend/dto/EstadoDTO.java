package com.zefuinha.spring_ionic_backend.dto;

import java.io.Serializable;

import com.zefuinha.spring_ionic_backend.domain.Estado;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EstadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;

	public EstadoDTO(Estado estado) {
		id = estado.getId();
		nome = estado.getNome();
	}
}
