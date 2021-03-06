package com.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.api.model.CidadeDTO;
import com.algafood.domain.model.Cidade;

@Component
public class CidadeDTOAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public CidadeDTO toDTO(Cidade cidade) {
		return modelMapper.map(cidade, CidadeDTO.class);
	}
	
	public List<CidadeDTO> toListDTO(List<Cidade> cidades) {
		return cidades.stream().map(this::toDTO).collect(Collectors.toList());
	}
}
