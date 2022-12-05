package com.algafood.api.v2.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.api.v2.model.input.CidadeInputDTOV2;
import com.algafood.domain.model.Cidade;
import com.algafood.domain.model.Estado;

@Component
public class CidadeInputDTODisassemblerV2 {

	@Autowired
	private ModelMapper modelMapper;
	
	public Cidade toDomainObject(CidadeInputDTOV2 cidadeInputDTO) {
		return modelMapper.map(cidadeInputDTO, Cidade.class);
	}
	
	public void copyToDomainObject(CidadeInputDTOV2 cidadeInputDTO, Cidade cidade) {
		// Para evitar  org.hibernate.HibernateException: identifier of an instance of
	    //com.algafood.domain.model.Cozinha was altered from 1 to 2
		cidade.setEstado(new Estado());
		
		modelMapper.map(cidadeInputDTO, cidade);
	}
}
