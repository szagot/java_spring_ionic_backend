package com.zefuinha.spring_ionic_backend.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.zefuinha.spring_ionic_backend.security.UserSecurity;

public class UserService {

	/**
	 * Devolve o usuário autenticado
	 * 
	 * @return
	 */
	public static UserSecurity authenticated() {
		// Se não tivber um usuário logado, devolve null
		try {
			return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}
