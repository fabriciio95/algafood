package com.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algafood.AlgafoodApplication;

public class ConsultaCozinhaMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class).web(WebApplicationType.NONE)
					.run(args);
		
		CadastroCozinha  cadastroCozinha =  applicationContext.getBean(CadastroCozinha.class);
		
		List<Cozinha> cozinhas = cadastroCozinha.listar();
		
		cozinhas.forEach(System.out::println);
	}
}
