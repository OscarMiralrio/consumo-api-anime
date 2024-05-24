package com.aluracursos.apianime;

import com.aluracursos.apianime.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApianimeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApianimeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal();
		principal.muestraDatos();

	}
}
