package com.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.controller.CozinhaController;
import com.algafood.api.model.CozinhaDTO;
import com.algafood.api.utils.AlgaLinks;
import com.algafood.domain.model.Cozinha;

@Component
public class CozinhaDTOAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public CozinhaDTOAssembler() {
		super(CozinhaController.class, CozinhaDTO.class);
	}
	
	@Override
	public CozinhaDTO toModel(Cozinha cozinha) {
		CozinhaDTO cozinhaDTO = createModelWithId(cozinha.getId(), cozinha);
		
		 modelMapper.map(cozinha, cozinhaDTO);
		 
		 cozinhaDTO.add(algaLinks.linkToCozinhas("cozinhas"));
		 
		 return cozinhaDTO;
	}
	
	
}
