package com.zefuinha.spring_ionic_backend.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private JWTUtil jwtUtil;
	private UserDetailsService userDetailService;

	/**
	 * Construtor
	 * 
	 * @param authenticationManager
	 * @param jwtUtil
	 * @param userDetailService
	 */
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil,
			UserDetailsService userDetailService) {
		super(authenticationManager);

		this.jwtUtil = jwtUtil;
		this.userDetailService = userDetailService;
	}

	/**
	 * Filtra as requisições
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// Pega o valor do Authorization enviado pelo Header
		String headerAuth = request.getHeader("Authorization");

		// Existe o cabeçalho? Começa com "Bearer "?
		if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
			// Verifica se o token é válido, passando o request e o token
			UsernamePasswordAuthenticationToken auth = getAuthentication(headerAuth.substring(7));

			// Autenticado?
			if (auth != null) {
				// Libera a autorização
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}

		// Prossegue com a requisição
		chain.doFilter(request, response);
	}

	/**
	 * Autentica um token
	 * 
	 * @param request
	 * @param substring
	 * @return
	 */
	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		// Verifica se o token é válido
		if (jwtUtil.tokenValido(token)) {
			// Pega o username
			String username = jwtUtil.getUsername(token);
			// Pega o usuário no BD
			UserDetails user = userDetailService.loadUserByUsername(username);
			// Retorna o usuário autenticado
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}
		
		// Não é válido
		return null;
	}

}
