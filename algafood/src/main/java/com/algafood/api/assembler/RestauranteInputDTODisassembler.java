package com.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algafood.api.model.input.RestauranteInputDTO;
import com.algafood.domain.model.Cozinha;
import com.algafood.domain.model.Restaurante;

@Component
public class RestauranteInputDTODisassembler {

	
	public Restaurante toDomainObject(RestauranteInputDTO restauranteInputDTO) {
		 Restaurante restaurante = new Restaurante();
		 restaurante.setNome(restauranteInputDTO.getNome());
		 restaurante.setTaxaFrete(restauranteInputDTO.getTaxaFrete());
		 
		 Cozinha cozinha = new Cozinha();
		 cozinha.setId(restauranteInputDTO.getCozinha().getId());
		 
		 restaurante.setCozinha(cozinha);
		 
		 return restaurante;
		
	}
}
