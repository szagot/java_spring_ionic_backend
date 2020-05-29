package com.zefuinha.spring_ionic_backend.domain.enums;

/**
 * o "ROLE_" é exigência do framework
 */
public enum Perfil {
	// @formatter:off
	ADMIN(1, "ROLE_ADMIN"), 
	CLIENTE(2, "ROLE_CLIENTE");
	// @formatter:on

	private int cod;
	private String descricao;

	Perfil(int cod, String descricao) {
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
	public static Perfil toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (Perfil x : Perfil.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("ID inválido: " + cod);
	}
}
