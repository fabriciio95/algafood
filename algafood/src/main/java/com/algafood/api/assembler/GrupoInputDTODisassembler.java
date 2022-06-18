package com.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.api.model.input.GrupoInputDTO;
import com.algafood.domain.model.Grupo;

@Component
public class GrupoInputDTODisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Grupo toObjectDomain(GrupoInputDTO grupoInputDTO) {
		return modelMapper.map(grupoInputDTO, Grupo.class);
	}
	
	public void copyToDomainObject(GrupoInputDTO grupoInputDTO, Grupo grupo) {
		modelMapper.map(grupoInputDTO, grupo);
	}
}
