package com.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.algafood.api.v1.openapi.model.UsuarioModelOpenApi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "usuarios")
@Getter
@Setter
@Schema(implementation = UsuarioModelOpenApi.class)
public class UsuarioDTO extends RepresentationModel<UsuarioDTO> {

	private Long id;
	
	private String nome;
	
	private String email;
}
