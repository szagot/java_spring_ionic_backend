package com.zefuinha.spring_ionic_backend;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zefuinha.spring_ionic_backend.domain.Categoria;
import com.zefuinha.spring_ionic_backend.repositories.CategoriaRepository;

@SpringBootApplication
public class SpringIonicBackendApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

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

		// Criando categorias de teste
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));

	}

}
