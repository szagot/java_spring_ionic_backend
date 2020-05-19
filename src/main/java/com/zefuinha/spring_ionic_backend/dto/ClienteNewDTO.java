package com.zefuinha.spring_ionic_backend.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClienteNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private String email;
	private String cpfOuCnpj;
	private Integer pessoa;

	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;

	private String tel;
	private String tel2;
	private String tel3;

	private Integer cidadeId;

}
