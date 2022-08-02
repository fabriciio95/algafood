package com.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.api.model.FotoProdutoDTO;
import com.algafood.domain.model.FotoProduto;

@Component
public class FotoProdutoAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	
	public FotoProdutoDTO toDTO(FotoProduto foto) {
		return modelMapper.map(foto, FotoProdutoDTO.class);
	}
}
