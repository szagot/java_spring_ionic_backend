package com.zefuinha.spring_ionic_backend.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zefuinha.spring_ionic_backend.domain.enums.EstadoPagamento;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * \@Inheritance(strategy = [])
 * 
 * Indica que haverão classes filhas.
 * 
 * strategy = InheritanceType.JOINED -> Para gerar uma só tabela para as filhas
 * strategy = InheritanceType.SINGLE_TABLE -> Para gerar uma tabela para cada
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "estado", "pedido" })
@Entity(name = "pagamentos")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private Integer estado;

	/**
	 * \@MapsId faz com que o ID de pagamento seja o mesmo que o id do pedido
	 */
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "pedido_id")
	@MapsId
	private Pedido pedido;

	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		super();
		this.id = id;
		this.estado = (estado == null) ? null : estado.getCod();
		this.pedido = pedido;
	}

	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}

	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}

}
