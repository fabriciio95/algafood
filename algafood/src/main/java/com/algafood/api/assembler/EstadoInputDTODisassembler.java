package com.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.api.model.input.EstadoInputDTO;
import com.algafood.domain.model.Estado;

@Component
public class EstadoInputDTODisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Estado toDomainObject(EstadoInputDTO estadoInputDTO) {
		return modelMapper.map(estadoInputDTO, Estado.class);
	}
	
	public void copyToDomainObject(EstadoInputDTO estadoInputDTO, Estado estado) {
		modelMapper.map(estadoInputDTO, estado);
	}
}
