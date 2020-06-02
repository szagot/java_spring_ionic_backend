package com.zefuinha.spring_ionic_backend.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String generateToken(String username) {
		return Jwts.builder()
				// Usuario
				.setSubject(username)
				// Tempo de expiração do token
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				// Chave de encriptação
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				// Gera a string com o token
				.compact();
	}

	/**
	 * Valida um token
	 * 
	 * @param token
	 * @return
	 */
	public boolean tokenValido(String token) {
		// Pega as reivindicações do token
		Claims claims = getClaims(token);
		// Tem reivindicações?
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());

			// Tem dados e ainda não expirou
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Retorna as reivindicações do token se for válido
	 * 
	 * @param token
	 * @return
	 */
	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Retorna o username de um token
	 * 
	 * @param token
	 * @return
	 */
	public String getUsername(String token) {
		// Pega as reivindicações do token
		Claims claims = getClaims(token);
		// Tem reivindicações?
		if (claims != null) {
			// Retorna o usuário
			return claims.getSubject();
		}
		
		return null;
	}

}
