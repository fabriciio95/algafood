package com.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.algafood.api.v1.openapi.model.RestauranteApenasNomeModelOpenApi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
@Schema(implementation = RestauranteApenasNomeModelOpenApi.class)
public class RestauranteApenasNomeDTO extends RepresentationModel<RestauranteApenasNomeDTO> {

	private Long id;
	
	private String nome;
}
