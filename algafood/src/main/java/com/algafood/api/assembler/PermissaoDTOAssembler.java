package com.algafood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.api.model.PermissaoDTO;
import com.algafood.domain.model.Permissao;

@Component
public class PermissaoDTOAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public PermissaoDTO toDTO(Permissao permissao) {
		return modelMapper.map(permissao, PermissaoDTO.class);
	}
	
	public List<PermissaoDTO> toListDTO(Collection<Permissao> permissoes) {
		return permissoes.stream().map(this::toDTO).collect(Collectors.toList());
	}
}
