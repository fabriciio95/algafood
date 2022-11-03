package com.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.controller.UsuarioController;
import com.algafood.api.controller.UsuarioGrupoController;
import com.algafood.api.model.UsuarioDTO;
import com.algafood.domain.model.Usuario;

@Component
public class UsuarioDTOAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioDTO>{

	public UsuarioDTOAssembler() {
		super(UsuarioController.class, UsuarioDTO.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UsuarioDTO toModel(Usuario usuario) {
		UsuarioDTO usuarioDTO = createModelWithId(usuario.getId(), usuario);
		
		modelMapper.map(usuario, usuarioDTO);
		
		usuarioDTO.add(linkTo(methodOn(UsuarioController.class).listar()).withRel("usuarios"));
		
		usuarioDTO.add(linkTo(methodOn(UsuarioGrupoController.class)
						.listar(usuario.getId())).withRel("grupos-usuario"));
		
		return usuarioDTO;
	}
	
	@Override
	public CollectionModel<UsuarioDTO> toCollectionModel(Iterable<? extends Usuario> entities) {
		return super.toCollectionModel(entities)
					.add(linkTo(methodOn(UsuarioController.class).listar()).withSelfRel());
	}
}
