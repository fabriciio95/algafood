package com.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algafood.domain.repository.CozinhaRepository;

@SpringBootApplication
public class AlteracaoCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlteracaoCozinhaMain.class).web(WebApplicationType.NONE)
					.run(args);
		
		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
		
		cozinhaRepository.listar().forEach(System.out::println);
		
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setId(1L);
		cozinha1.setNome("Brasileira");
		cozinhaRepository.salvar(cozinha1);
		
		cozinhaRepository.listar().forEach(System.out::println);
	}
}
