package com.zefuinha.spring_ionic_backend.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "desconto", "quantidade", "preco" })
@Entity
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();

	private Double desconto;
	private Integer quantidade;
	private Double preco;

	/**
	 * Necessário por causa da chave dupla
	 * 
	 * @param pedido
	 * @param produto
	 * @param desconto
	 * @param quantidade
	 * @param preco
	 */
	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
		super();
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();
	}

	public void setPedido(Pedido pedido) {
		id.setPedido(pedido);
	}

	public Produto getProduto() {
		return id.getProduto();
	}

	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}

	/**
	 * Pega osubtotal de um produto
	 * 
	 * @return
	 */
	public Double getSubTotal() {
		return quantidade * (preco - desconto);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")); 
		
		// Nome do produto
		builder.append(getProduto().getNome());
		// Quantidade
		builder.append(", Qt: ");
		builder.append(getQuantidade());
		// Valor
		builder.append(", Preço unitário: ");
		builder.append(nf.format(getPreco()));
		// Subtotal
		builder.append(", Subtotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");

		return builder.toString();
	}
}

