package com.zefuinha.spring_ionic_backend.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zefuinha.spring_ionic_backend.domain.enums.TipoCliente;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "nome", "enderecos", "telefones", "pedidos" })
@Entity(name = "clientes")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String email;
	private String cpfOuCnpj;
	private Integer pessoa;

	@OneToMany(mappedBy = "cliente")
	private List<Endereco> enderecos = new ArrayList<>();

	/**
	 * Quando não tem uma classe para o campo estrangeiro, usa-se
	 * \@ElementCollection e \@CollectionTable
	 */
	@ElementCollection
	@CollectionTable(name = "telefones")
	private Set<String> telefones = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<>();

	/**
	 * Necessário para não incluir listas
	 * 
	 * @param id
	 * @param nome
	 * @param email
	 * @param cpfOuCnpj
	 * @param pessoa
	 */
	public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente pessoa) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.pessoa = (pessoa == null) ? null : pessoa.getCod();
	}

	/**
	 * Faz a conversão de inteiro para Enum
	 * 
	 * @return
	 */
	public TipoCliente getPessoa() {
		return TipoCliente.toEnum(pessoa);
	}

	/**
	 * Faz a conversão de Enum para inteiro
	 * 
	 * @param pessoa
	 */
	public void setPessoa(TipoCliente pessoa) {
		this.pessoa = pessoa.getCod();
	}

}
