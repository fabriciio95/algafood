package com.algafood.api.v1.assembler;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.api.v1.model.input.CozinhaInputDTO;
import com.algafood.domain.model.Cozinha;

@Component
public class CozinhaDTODisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Cozinha toDomainObject(CozinhaInputDTO cozinhaInputDTO) {
		return modelMapper.map(cozinhaInputDTO, Cozinha.class);
	}
	
	public void copyToDomainObject(CozinhaInputDTO cozinhaInputDTO, Cozinha cozinha) {
		// Para evitar  org.hibernate.HibernateException: identifier of an instance of
	    //com.algafood.domain.model.Cozinha was altered from 1 to 2
		cozinha.setRestaurantes(new ArrayList<>());
		
		modelMapper.map(cozinhaInputDTO, cozinha);
	}
}
