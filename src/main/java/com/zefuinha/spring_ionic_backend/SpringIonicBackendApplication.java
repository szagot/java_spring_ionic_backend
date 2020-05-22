package com.zefuinha.spring_ionic_backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIonicBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringIonicBackendApplication.class, args);
	}

	/**
	 * Faz as execuções necessários no starter da aplicação
	 * 
	 * Essa classe precisa implementar a interface CommandLineRunner
	 */
	@Override
	public void run(String... args) throws Exception {
		// Instancias das tabelas tranferidas para o serviço DBService
	}

}
