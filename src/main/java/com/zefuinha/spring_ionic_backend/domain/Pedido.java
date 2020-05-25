package com.zefuinha.spring_ionic_backend.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "criadoEm", "pagamento", "itens" })
@Entity
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date criadoEm;

	@OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
	private Pagamento pagamento;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "endereco_de_entrega_id")
	private Endereco enderecoDeEntrega;

	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> itens = new HashSet<>();

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

	/**
	 * Devolve o total do pedido
	 * 
	 * @return
	 */
	public Double getValorTotal() {
		Double total = .0;
		for (ItemPedido pedido : itens) {
			total += pedido.getSubTotal();
		}

		return total;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		// ID
		builder.append("Pedido Número: ");
		builder.append(getId());
		// Data
		builder.append(", Criado em: ");
		builder.append(sdf.format(getCriadoEm()));
		// Cliente
		builder.append(", Cliente: ");
		builder.append(getCliente().getNome());
		// Status pagamento
		builder.append(", Situação do pagamento: ");
		builder.append(getPagamento().getEstado().getDescricao());
		// Produtos
		builder.append("\nItens do Pedido:\n");
		for (ItemPedido item : getItens()) {
			builder.append("  • ");
			builder.append(item);
		}
		builder.append("Valor Total: R$ ");
		builder.append(nf.format(getValorTotal()));

		return builder.toString();
	}

}
