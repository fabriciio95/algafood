package com.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.controller.CidadeController;
import com.algafood.api.controller.EstadoController;
import com.algafood.api.model.CidadeDTO;
import com.algafood.domain.model.Cidade;

@Component
public class CidadeDTOAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeDTO>{

	public CidadeDTOAssembler() {
		super(CidadeController.class, CidadeDTO.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CidadeDTO toModel(Cidade cidade) {
		CidadeDTO cidadeDTO = createModelWithId(cidade.getId(), cidade);
		
		modelMapper.map(cidade, cidadeDTO);
		
//		CidadeDTO cidadeDTO = modelMapper.map(cidade, CidadeDTO.class);
		 
//		 cidadeDTO.add(linkTo(methodOn(CidadeController.class)
//				 .buscar(cidadeDTO.getId())).withSelfRel());
		 
		 cidadeDTO.add(linkTo(methodOn(CidadeController.class).listar()).withRel("cidades"));
		 
		 cidadeDTO.getEstado().add(linkTo(methodOn(EstadoController.class)
				 .buscar(cidadeDTO.getEstado().getId())).withSelfRel());
		 
		 return cidadeDTO;
	}
	
	@Override
	public CollectionModel<CidadeDTO> toCollectionModel(Iterable<? extends Cidade> entities) {
		return super.toCollectionModel(entities)
					.add(linkTo(methodOn(CidadeController.class).listar()).withSelfRel());
	}
}
