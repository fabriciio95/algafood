package com.algafood.api.v1.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.algafood.api.v1.openapi.model.RestauranteBasicoOpenApiDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
@Schema(implementation = RestauranteBasicoOpenApiDTO.class)
public class RestauranteBasicoDTO extends RepresentationModel<RestauranteBasicoDTO> {
	
	private Long id;
	
	private String nome;
	
	private BigDecimal taxaFrete;
	
	private CozinhaDTO cozinha;
}
