package com.zefuinha.spring_ionic_backend.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.zefuinha.spring_ionic_backend.services.validation.ClienteInsert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@ClienteInsert // Validator personalizado
public class ClienteNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 150, message = "O tamanho deve ser entre 5 e 150 caracteres")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String cpfOuCnpj;

	private Integer pessoa;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String senha;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String logradouro;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String numero;

	private String complemento;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String bairro;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String cep;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String tel;

	private String tel2;
	private String tel3;

	private Integer cidadeId;

}
