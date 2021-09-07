package com.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algafood.AlgafoodApplication;
import com.algafood.domain.model.Restaurante;
import com.algafood.domain.repository.RestauranteRepository;

public class ExclusaoRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class).web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		
		restauranteRepository.listar().forEach(System.out::println);
		
		Restaurante restaurante = new Restaurante();
		restaurante.setId(1L);
		
		restauranteRepository.remover(restaurante);
		
		restauranteRepository.listar().forEach(System.out::println);
	}
}
