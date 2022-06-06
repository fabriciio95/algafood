package com.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.api.model.RestauranteDTO;
import com.algafood.domain.model.Restaurante;

@Component
public class RestauranteDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;

	
	public RestauranteDTO toDTO(Restaurante restaurante) {
		  return modelMapper.map(restaurante, RestauranteDTO.class);
	}
	
	public List<RestauranteDTO> toListDTO(List<Restaurante> restaurantes) {
		return restaurantes.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
}
