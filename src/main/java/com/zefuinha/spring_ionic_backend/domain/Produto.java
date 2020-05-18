package com.zefuinha.spring_ionic_backend.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Produtos usando os anotations do lombok
 * 
 * https://projectlombok.org/features/all
 */

@NoArgsConstructor
@EqualsAndHashCode(exclude = { "preco", "categorias" })
@Getter
@Setter
@Entity(name = "produtos")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double preco;

	@JsonBackReference
	@ManyToMany
	@JoinTable(
		name = "categorias_do_produto", 
		joinColumns = @JoinColumn(name = "produto_id"), 
		inverseJoinColumns = @JoinColumn(name = "categoria_id")
	)
	private List<Categoria> categorias = new ArrayList<>();

	/**
	 * Necessário criar o construtor pq a lista de categorias não vai nele
	 * 
	 * @param id
	 * @param nome
	 * @param preco
	 */
	public Produto(Integer id, String nome, Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

}
