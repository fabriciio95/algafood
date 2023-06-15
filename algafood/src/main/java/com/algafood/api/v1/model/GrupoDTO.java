package com.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.algafood.api.v1.openapi.model.GrupoDTOOpenApi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "grupos")
@Setter
@Getter
@Schema(implementation = GrupoDTOOpenApi.class)
public class GrupoDTO extends RepresentationModel<GrupoDTO> {

	private Long id;
	
	private String nome;
}
