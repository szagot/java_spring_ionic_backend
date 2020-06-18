package com.zefuinha.spring_ionic_backend.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zefuinha.spring_ionic_backend.dto.EmailDTO;
import com.zefuinha.spring_ionic_backend.security.JWTUtil;
import com.zefuinha.spring_ionic_backend.security.UserSecurity;
import com.zefuinha.spring_ionic_backend.services.AuthService;
import com.zefuinha.spring_ionic_backend.services.UserService;

/**
 * Recursos para autenticação
 * 
 * (/login é padrão do framework, e por isso não entra aqui)
 */

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthService service;

	/**
	 * POST /auth/refresh_token
	 * 
	 * Gera um novo token
	 * 
	 * @param response
	 * @return
	 */
	@PostMapping(value = "/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSecurity user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		// Permite ao CORS exibir o cabeçalho de autenticação
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}

	/**
	 * POST /auth/forgot
	 * 
	 * Esqueci minha senha
	 * 
	 * @param response
	 * @return
	 */
	@PostMapping(value = "/forgot")
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO email) {
		service.sendNewPassword(email.getEmail());
		return ResponseEntity.noContent().build();
	}

}
