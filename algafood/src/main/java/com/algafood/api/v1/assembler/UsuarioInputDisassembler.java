package com.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.api.v1.model.input.UsuarioInputDTO;
import com.algafood.domain.model.Usuario;

@Component
public class UsuarioInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Usuario toDomainObject(UsuarioInputDTO usuarioInputDTO) {
		return modelMapper.map(usuarioInputDTO, Usuario.class);
	}
	
	public void copyToDomainObject(UsuarioInputDTO usuarioInputDTO, Usuario usuario) {
		modelMapper.map(usuarioInputDTO, usuario);
	}
}
