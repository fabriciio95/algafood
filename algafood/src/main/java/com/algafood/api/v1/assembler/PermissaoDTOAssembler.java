package com.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.v1.controller.PermissaoController;
import com.algafood.api.v1.model.PermissaoDTO;
import com.algafood.api.v1.utils.AlgaLinks;
import com.algafood.core.security.AlgaSecurity;
import com.algafood.domain.model.Permissao;

@Component
public class PermissaoDTOAssembler extends RepresentationModelAssemblerSupport<Permissao, PermissaoDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	public PermissaoDTOAssembler() {
		super(PermissaoController.class, PermissaoDTO.class);
	}
	
	@Override
	public PermissaoDTO toModel(Permissao permissao) {
		return modelMapper.map(permissao, PermissaoDTO.class);
	}
	
	@Override
	public CollectionModel<PermissaoDTO> toCollectionModel(Iterable<? extends Permissao> entities) {
		CollectionModel<PermissaoDTO> collectionDTO =  super.toCollectionModel(entities);
				
		if(algaSecurity.podeConsultarUsuariosGruposPermissoes())
			collectionDTO.add(algaLinks.linkToPermissoes());
				
		
		return collectionDTO;
	}
}
