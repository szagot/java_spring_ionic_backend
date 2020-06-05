package com.zefuinha.spring_ionic_backend.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.zefuinha.spring_ionic_backend.domain.Cliente;
import com.zefuinha.spring_ionic_backend.repositories.ClienteRepository;
import com.zefuinha.spring_ionic_backend.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private EmailService emailService;

	private Random rand = new Random();

	/**
	 * Envia uma nova senha (esqueci minha senha)
	 * 
	 * @param email
	 */
	public void sendNewPassword(String email) {
		Cliente cliente = clienteRepository.findByEmail(email);

		// Cliente existe?
		if (cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}

		// Gera uma nova senha
		String newPass = newPassword();

		// Salva a nova senha
		cliente.setSenha(pe.encode(newPass));
		clienteRepository.save(cliente);

		// Envia email
		emailService.sendNewPasswordEmail(cliente, newPass);

	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < vet.length; i++) {
			vet[i] = randomChar();
		}

		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		// Gera um dígito
		if (opt == 0) {
			// devolve um dígito (de 48 a 58 da tabela ASCII) 
			return (char) (rand.nextInt(10) + 48);
		}
		// Gera uma letra maiúscula
		else if (opt == 1) {
			// devolve uma letra maiúscula (de 65 a 91 da tabela ASCII) 
			return (char) (rand.nextInt(26) + 65);
		}
		
		// devolve uma letra minuscula (de 97 a 123 da tabela ASCII) 
		return (char) (rand.nextInt(26) + 97);
	}

}
