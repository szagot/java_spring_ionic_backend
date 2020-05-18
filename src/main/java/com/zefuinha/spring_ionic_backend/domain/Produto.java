package com.zefuinha.spring_ionic_backend.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Produtos usando os anotations do lombok
 * 
 * https://projectlombok.org/features/all
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "preco", "categorias", "itens" })
@Entity(name = "produtos")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double preco;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "categorias_do_produto", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private List<Categoria> categorias = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "id.produto")
	private Set<ItemPedido> itens = new HashSet<>();

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

	/**
	 * Devolve a lista de pedidos associados a esse produto
	 * 
	 * @return
	 */
	@JsonIgnore
	public List<Pedido> getPedidos() {
		List<Pedido> pedidos = new ArrayList<>();

		for (ItemPedido x : itens) {
			pedidos.add(x.getPedido());
		}

		return pedidos;
	}

}
