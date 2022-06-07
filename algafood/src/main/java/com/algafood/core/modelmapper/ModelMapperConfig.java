package com.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algafood.api.model.RestauranteDTO;
import com.algafood.domain.model.Restaurante;

@Configuration
public class ModelMapperConfig {

	
	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		modelMapper.createTypeMap(Restaurante.class, RestauranteDTO.class)
			.addMapping(Restaurante::getTaxaFrete, RestauranteDTO::setPrecoFrete);
		
		return modelMapper;
	}
}
