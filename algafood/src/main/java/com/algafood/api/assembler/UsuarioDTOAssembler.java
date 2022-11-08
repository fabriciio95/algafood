package com.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.controller.UsuarioController;
import com.algafood.api.model.UsuarioDTO;
import com.algafood.api.utils.AlgaLinks;
import com.algafood.domain.model.Usuario;

@Component
public class UsuarioDTOAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioDTO>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;

	public UsuarioDTOAssembler() {
		super(UsuarioController.class, UsuarioDTO.class);
	}
	
	@Override
	public UsuarioDTO toModel(Usuario usuario) {
		UsuarioDTO usuarioDTO = createModelWithId(usuario.getId(), usuario);
		
		modelMapper.map(usuario, usuarioDTO);
		
		usuarioDTO.add(algaLinks.linkToUsuarios("usuarios"));
		
		usuarioDTO.add(algaLinks.linkToGruposUsuarios(usuarioDTO.getId(), "grupos-usuario"));
		
		return usuarioDTO;
	}
	
	@Override
	public CollectionModel<UsuarioDTO> toCollectionModel(Iterable<? extends Usuario> entities) {
		return super.toCollectionModel(entities)
					.add(algaLinks.linkToUsuarios());
	}
}
