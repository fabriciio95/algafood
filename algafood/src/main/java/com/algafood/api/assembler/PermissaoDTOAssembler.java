package com.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.controller.PermissaoController;
import com.algafood.api.model.PermissaoDTO;
import com.algafood.api.utils.AlgaLinks;
import com.algafood.domain.model.Permissao;

@Component
public class PermissaoDTOAssembler extends RepresentationModelAssemblerSupport<Permissao, PermissaoDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public PermissaoDTOAssembler() {
		super(PermissaoController.class, PermissaoDTO.class);
	}
	
	@Override
	public PermissaoDTO toModel(Permissao permissao) {
		return modelMapper.map(permissao, PermissaoDTO.class);
	}
	
	@Override
	public CollectionModel<PermissaoDTO> toCollectionModel(Iterable<? extends Permissao> entities) {
		return super.toCollectionModel(entities)
				.add(algaLinks.linkToPermissoes());
	}
}
