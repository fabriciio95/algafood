package com.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.algafood.api.v1.openapi.model.CidadeModelOpenApi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidades")
@Getter
@Setter
@Schema(name = "CidadeDTO", implementation = CidadeModelOpenApi.class)
public class CidadeDTO extends RepresentationModel<CidadeDTO>{

	private Long id;
	
	private String nome;
	
	private EstadoDTO estado;
}
