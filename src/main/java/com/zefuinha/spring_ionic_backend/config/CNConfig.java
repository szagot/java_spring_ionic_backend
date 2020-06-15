package com.zefuinha.spring_ionic_backend.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

/**
 * Cloudnary: Configurações de imagens
 */

@Configuration
public class CNConfig {

	@Value("${cn.cloud_name}")
	private String cloudName;
	@Value("${cn.api_key}")
	private String apiKey;
	@Value("${cn.api_secret}")
	private String apiSecret;

	@Bean
	public Cloudinary cnClient() {
		
		Map<String, String> config = new HashMap<String, String>();
		config.put("cloud_name", cloudName);
		config.put("api_key", apiKey);
		config.put("api_secret", apiSecret);
		return new Cloudinary(config);
	}

}
