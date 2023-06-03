package com.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.algafood.api.v1.openapi.model.EstadoModelOpenApi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "estados")
@Getter
@Setter
@Schema(name = "EstadoDTO", implementation = EstadoModelOpenApi.class)
public class EstadoDTO extends RepresentationModel<EstadoDTO> {

	private Long id;
	
	private String nome;
}
