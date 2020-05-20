package com.zefuinha.spring_ionic_backend.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.zefuinha.spring_ionic_backend.domain.Cliente;
import com.zefuinha.spring_ionic_backend.services.validation.ClienteUpdate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@ClienteUpdate // Validator personalizado
public class ClienteDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Apenas campos alteráveis
	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 150, message = "O tamanho deve ser entre 5 e 150 caracteres")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;

	public ClienteDTO(Cliente cliente) {
		id = cliente.getId();
		nome = cliente.getNome();
		email = cliente.getEmail();
	}
}
