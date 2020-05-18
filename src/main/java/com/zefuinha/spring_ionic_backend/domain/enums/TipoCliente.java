package com.zefuinha.spring_ionic_backend.domain.enums;

public enum TipoCliente {

	// @formatter:off
	PF(1, "Pessoa Física"), 
	PJ(2, "Pessoa Jurídica");
	// @formatter:on

	private int cod;
	private String descricao;

	private TipoCliente(int cod, String descricao) {
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
	public static TipoCliente toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (TipoCliente x : TipoCliente.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("ID inválido: " + cod);
	}

}
