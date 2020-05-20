package com.zefuinha.spring_ionic_backend.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.zefuinha.spring_ionic_backend.domain.Cliente;
import com.zefuinha.spring_ionic_backend.dto.ClienteDTO;
import com.zefuinha.spring_ionic_backend.repositories.ClienteRepository;
import com.zefuinha.spring_ionic_backend.resources.exceptions.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	ClienteRepository repository;

	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext context) {
		// Erros personalizados
		List<FieldMessage> list = new ArrayList<>();

		// Pegando o ID da URI
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));

		// Verificando se o email já existe em outro usuário
		Cliente aux = repository.findByEmail(clienteDTO.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
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