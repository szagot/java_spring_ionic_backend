package com.zefuinha.spring_ionic_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zefuinha.spring_ionic_backend.domain.PagamentoComBoleto;
import com.zefuinha.spring_ionic_backend.domain.PagamentoComCartao;

/**
 * Classe de configuração do JSON
 * 
 * https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare
 */

@Configuration
public class JacksonConfig {
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {

				// Registrando as subclasses para inserção
				objectMapper.registerSubtypes(PagamentoComCartao.class);
				objectMapper.registerSubtypes(PagamentoComBoleto.class);
				
				super.configure(objectMapper);
			};
		};
		return builder;
	}
}