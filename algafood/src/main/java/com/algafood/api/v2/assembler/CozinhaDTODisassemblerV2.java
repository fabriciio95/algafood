package com.algafood.api.v2.assembler;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.api.v2.model.input.CozinhaInputDTOV2;
import com.algafood.domain.model.Cozinha;

@Component
public class CozinhaDTODisassemblerV2 {

	@Autowired
	private ModelMapper modelMapper;
	
	public Cozinha toDomainObject(CozinhaInputDTOV2 cozinhaInputDTO) {
		return modelMapper.map(cozinhaInputDTO, Cozinha.class);
	}
	
	public void copyToDomainObject(CozinhaInputDTOV2 cozinhaInputDTO, Cozinha cozinha) {
		// Para evitar  org.hibernate.HibernateException: identifier of an instance of
	    //com.algafood.domain.model.Cozinha was altered from 1 to 2
		cozinha.setRestaurantes(new ArrayList<>());
		
		modelMapper.map(cozinhaInputDTO, cozinha);
	}
}
