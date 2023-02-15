package com.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.v1.controller.GrupoController;
import com.algafood.api.v1.model.GrupoDTO;
import com.algafood.api.v1.utils.AlgaLinks;
import com.algafood.core.security.AlgaSecurity;
import com.algafood.domain.model.Grupo;

@Component
public class GrupoDTOAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoDTO> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;

	public GrupoDTOAssembler() {
		super(GrupoController.class, GrupoDTO.class);
	}

	@Override
	public GrupoDTO toModel(Grupo grupo) {
		GrupoDTO grupoDTO = createModelWithId(grupo.getId(), grupo);
		
		modelMapper.map(grupo, grupoDTO);
		
		if(algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
			grupoDTO.add(algaLinks.linkToGrupos("grupos"));
			
			grupoDTO.add(algaLinks.linkToGrupoPermissoes(grupo.getId(), "permissoes"));
			
		}
		
		return grupoDTO;
	}

	@Override
	public CollectionModel<GrupoDTO> toCollectionModel(Iterable<? extends Grupo> entities) {
		CollectionModel<GrupoDTO> collectionDto =  super.toCollectionModel(entities);
			
		if(algaSecurity.podeConsultarUsuariosGruposPermissoes())
			collectionDto.add(algaLinks.linkToGrupos());
				
		return collectionDto;
	}
}
