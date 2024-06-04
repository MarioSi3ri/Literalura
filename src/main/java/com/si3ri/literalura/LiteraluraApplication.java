package com.si3ri.literalura;

import com.si3ri.literalura.menu.Menu;
import com.si3ri.literalura.repository.AutoresRepository;
import com.si3ri.literalura.repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner { // 'CommandLineRunner' es una interfaz de Spring Boot que indica que una clase debe ejecutarse cuando la aplicación se inicia.

	// Se inyectan automáticamente los beans 'LibrosRepository' y 'AutoresRepository' en esta clase.
	@Autowired
	private LibrosRepository repo1;

	@Autowired
	private AutoresRepository repo2;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Menu menu = new Menu(repo1, repo2); // Método que se ejecuta cuando se inicia la aplicación. Crea un nuevo objeto menu y llama al método 'Menu()'.
		menu.Menu();
	}

}