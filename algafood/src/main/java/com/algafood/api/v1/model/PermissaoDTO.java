package com.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.algafood.api.v1.openapi.model.PermissaoDTOOpenApi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "permissoes")
@Getter
@Setter
@Schema(implementation = PermissaoDTOOpenApi.class)
public class PermissaoDTO extends RepresentationModel<PermissaoDTO> {

	private Long id;
	
	private String nome;
	
	private String descricao;
	
}
