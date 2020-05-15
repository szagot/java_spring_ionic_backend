package com.zefuinha.spring_ionic_backend.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Categoria usando os anotations do lombok
 * 
 * https://projectlombok.org/features/all
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;

}
