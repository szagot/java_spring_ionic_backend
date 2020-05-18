package com.zefuinha.spring_ionic_backend.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Categoria usando os anotations do lombok
 * 
 * https://projectlombok.org/features/all
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "produtos" })
@Entity(name = "categorias")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;

	@JsonManagedReference
	@ManyToMany(mappedBy = "categorias")
	private List<Produto> produtos = new ArrayList<>();

	/**
	 * Necessário criar construtor pq a lista de produtos não vai nele
	 * 
	 * @param id
	 * @param nome
	 */
	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

}
