package com.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AlteracaoCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlteracaoCozinhaMain.class).web(WebApplicationType.NONE)
					.run(args);
		
		CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
		
		cadastroCozinha.listar().forEach(System.out::println);
		
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setId(1L);
		cozinha1.setNome("Brasileira");
		cadastroCozinha.salvar(cozinha1);
		
		cadastroCozinha.listar().forEach(System.out::println);
	}
}
