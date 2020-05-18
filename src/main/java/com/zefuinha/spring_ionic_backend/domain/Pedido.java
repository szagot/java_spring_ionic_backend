package com.zefuinha.spring_ionic_backend.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "criadoEm", "pagamento" })
@Entity(name = "pedidos")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date criadoEm;

	@JsonManagedReference
	@OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
	private Pagamento pagamento;

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "endereco_de_entrega_id")
	private Endereco enderecoDeEntrega;

	/**
	 * Necessário para não ter de instanciar o pagamento junto
	 * 
	 * @param id
	 * @param criadoEm
	 * @param cliente
	 * @param enderecoDeEntrega
	 */
	public Pedido(Integer id, Date criadoEm, Cliente cliente, Endereco enderecoDeEntrega) {
		super();
		this.id = id;
		this.criadoEm = criadoEm;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

}
