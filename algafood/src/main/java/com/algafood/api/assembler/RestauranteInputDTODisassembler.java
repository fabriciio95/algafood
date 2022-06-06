package com.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.api.model.input.RestauranteInputDTO;
import com.algafood.domain.model.Restaurante;

@Component
public class RestauranteInputDTODisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	
	public Restaurante toDomainObject(RestauranteInputDTO restauranteInputDTO) {
		return modelMapper.map(restauranteInputDTO, Restaurante.class);
	}
}
