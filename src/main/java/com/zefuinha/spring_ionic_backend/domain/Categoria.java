package com.zefuinha.spring_ionic_backend.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
@Entity(name = "categorias")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;

}
