package com.zefuinha.spring_ionic_backend.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.zefuinha.spring_ionic_backend.domain.enums.Perfil;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserSecurity implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String email;
	private String senha;
	// Perfis
	private Collection<? extends GrantedAuthority> authorities;

	public UserSecurity(Integer id, String email, String senha, Set<Perfil> perfis) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		// Converte s lista de perfis para uma lista de autorizações (ROLE_{tipo})
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao()))
				.collect(Collectors.toList());
	}

	/**
	 * Perfis do usuário
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	/**
	 * A conta não está expirada?
	 */
	@Override
	public boolean isAccountNonExpired() {
		// Por padrão, aqui não expira
		return true;
	}

	/**
	 * A conta não está bloqueada?
	 */
	@Override
	public boolean isAccountNonLocked() {
		// Por padrão, aqui não bloqueia a conta
		return true;
	}

	/**
	 * As credenciais expiraram?
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		// Por padrão, aqui não expira
		return true;
	}

	/**
	 * O usuário está ativo?
	 */
	@Override
	public boolean isEnabled() {
		// Por padrão o usuário está sempre ativo
		return true;
	}

}
