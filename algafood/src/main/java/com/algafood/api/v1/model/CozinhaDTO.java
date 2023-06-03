package com.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.algafood.api.v1.openapi.model.CozinhaDTOOpenApi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cozinhas")
@Getter
@Setter
@Schema(name = "CozinhaDTO", implementation = CozinhaDTOOpenApi.class)
public class CozinhaDTO extends RepresentationModel<CozinhaDTO> {

	private Long id;
	
	private String nome;
}
