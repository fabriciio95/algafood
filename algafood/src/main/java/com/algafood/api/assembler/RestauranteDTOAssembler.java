package com.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.algafood.api.model.CozinhaDTO;
import com.algafood.api.model.RestauranteDTO;
import com.algafood.domain.model.Restaurante;

@Component
public class RestauranteDTOAssembler {

	
	public RestauranteDTO toDTO(Restaurante restaurante) {
		   RestauranteDTO restauranteDTO = new RestauranteDTO();
		   restauranteDTO.setId(restaurante.getId());
		   restauranteDTO.setNome(restaurante.getNome());
		   restauranteDTO.setTaxaFrete(restaurante.getTaxaFrete());
		   restauranteDTO.setCozinha(new CozinhaDTO());
		   restauranteDTO.getCozinha().setId(restaurante.getCozinha().getId());
		   restauranteDTO.getCozinha().setNome(restaurante.getCozinha().getNome());
		   return restauranteDTO;
	}
	
	public List<RestauranteDTO> toListDTO(List<Restaurante> restaurantes) {
		return restaurantes.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
}
