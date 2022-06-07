package com.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.api.model.input.RestauranteInputDTO;
import com.algafood.domain.model.Cozinha;
import com.algafood.domain.model.Restaurante;

@Component
public class RestauranteInputDTODisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	
	public Restaurante toDomainObject(RestauranteInputDTO restauranteInputDTO) {
		return modelMapper.map(restauranteInputDTO, Restaurante.class);
	}
	
	
	public void copyToDomainObject(RestauranteInputDTO restauranteInputDTO, Restaurante restaurante) {
		// Para evitar  org.hibernate.HibernateException: identifier of an instance of
		//com.algafood.domain.model.Cozinha was altered from 1 to 2
		restaurante.setCozinha(new Cozinha());
		
		modelMapper.map(restauranteInputDTO, restaurante);
	}
}
