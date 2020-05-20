package com.zefuinha.spring_ionic_backend.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.zefuinha.spring_ionic_backend.domain.Cliente;
import com.zefuinha.spring_ionic_backend.domain.enums.TipoCliente;
import com.zefuinha.spring_ionic_backend.dto.ClienteNewDTO;
import com.zefuinha.spring_ionic_backend.repositories.ClienteRepository;
import com.zefuinha.spring_ionic_backend.resources.exceptions.FieldMessage;
import com.zefuinha.spring_ionic_backend.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	ClienteRepository repository;

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO clienteDTO, ConstraintValidatorContext context) {
		// Erros personalizados
		List<FieldMessage> list = new ArrayList<>();

		// É PF e o CPF não é válido?
		if (clienteDTO.getPessoa().equals(TipoCliente.PF.getCod()) && !BR.isValidCPF(clienteDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));
		}
		// É PJ e o CPF não é válido?
		if (clienteDTO.getPessoa().equals(TipoCliente.PJ.getCod()) && !BR.isValidCNPJ(clienteDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
		}

		// Verificando se o email já existe
		Cliente aux = repository.findByEmail(clienteDTO.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "Esse email já cadastrado para outro cliente"));
		}

		// Pegando os erros personalizados e incluindo na lista do framework
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}