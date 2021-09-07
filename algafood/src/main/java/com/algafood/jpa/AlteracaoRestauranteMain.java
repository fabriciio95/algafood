package com.algafood.jpa;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algafood.AlgafoodApplication;
import com.algafood.domain.model.Restaurante;
import com.algafood.domain.repository.RestauranteRepository;

public class AlteracaoRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class).web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		
		System.out.println(restauranteRepository.buscar(1L));
		
		Restaurante restaurante = new Restaurante();
		restaurante.setId(1L);
		restaurante.setNome("Caseirinho");
		restaurante.setTaxaFrete(new BigDecimal(10));
		
		restauranteRepository.salvar(restaurante);
		
		System.out.println(restauranteRepository.buscar(1L));
	}
}
