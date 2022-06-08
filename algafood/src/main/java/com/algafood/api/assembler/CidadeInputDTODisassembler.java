package com.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.api.model.input.CidadeInputDTO;
import com.algafood.domain.model.Cidade;
import com.algafood.domain.model.Estado;

@Component
public class CidadeInputDTODisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Cidade toDomainObject(CidadeInputDTO cidadeInputDTO) {
		return modelMapper.map(cidadeInputDTO, Cidade.class);
	}
	
	public void copyToDomainObject(CidadeInputDTO cidadeInputDTO, Cidade cidade) {
		// Para evitar  org.hibernate.HibernateException: identifier of an instance of
	    //com.algafood.domain.model.Cozinha was altered from 1 to 2
		cidade.setEstado(new Estado());
		
		modelMapper.map(cidadeInputDTO, cidade);
	}
}
