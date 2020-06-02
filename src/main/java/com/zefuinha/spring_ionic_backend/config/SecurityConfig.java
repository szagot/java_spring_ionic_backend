package com.zefuinha.spring_ionic_backend.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.zefuinha.spring_ionic_backend.security.JWTAuthenticationFilter;
import com.zefuinha.spring_ionic_backend.security.JWTAuthorizationFilter;
import com.zefuinha.spring_ionic_backend.security.JWTUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// Pega os profiles
	@Autowired
	private Environment env;

	// Apesar de injetar UserDetailsService, o spring irá encontra o
	// UserDetailsServiceImpl
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTUtil jwtUtil;

	// Quais endpoints estão liberados (sem necessidade de tolen)
	private static final String[] PUBLIC_MATCHERS = {
			// Console do BD da área de testes
			"/h2-console/**" };

	// Quais endpoints estão liberados apenas para GET (sem necessidade de tolen)
	private static final String[] PUBLIC_MATCHERS_GET = {
			// Produtos
			"/produtos/**",
			// Categorias
			"/categorias/**" };

	/**
	 * Configuraçõe de autenticação
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Se for o perfil de teste
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			// Permite o acesso ao /h2-console/
			http.headers().frameOptions().disable();
		}

		// Aplica o Bean de configuração de CORS abaixo
		http.cors()
				// Desabilita a proteção CSRF pois a autenticação não é feita por sessão, mas
				// sim por token a cada requisição
				.and().csrf().disable();

		http.authorizeRequests()
				// Autoriza os endpoints de PUBLIC_MATCHERS
				.antMatchers(PUBLIC_MATCHERS).permitAll()
				// Autoriza os endpoints de PUBLIC_MATCHERS_GET apenas para leitura
				.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
				// Para todas as outras, exija autorização
				.anyRequest().authenticated();

		// Adiciona o filtro de autenticação
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));

		// Adiciona o filtro de autorização de authenticação
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));

		// Impede a criação de sessões
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	/**
	 * Mecanismo de autenticação
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	/**
	 * Especifica que é permitido o acesso de múltiplas fontes
	 * 
	 * @return
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

	/**
	 * Bean para encriptação de senha
	 * 
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
