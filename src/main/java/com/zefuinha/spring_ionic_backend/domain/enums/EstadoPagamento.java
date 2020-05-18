package com.zefuinha.spring_ionic_backend.domain.enums;

public enum EstadoPagamento {
	// @formatter:off
	PENDENTE(1, "Pendente"), 
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado"),;
	// @formatter:on

	private int cod;
	private String descricao;

	EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	/**
	 * Retorna o ENUM conforme o çodigo informado
	 * 
	 * @param cod
	 * @return
	 */
	public static EstadoPagamento toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("ID inválido: " + cod);
	}
}
