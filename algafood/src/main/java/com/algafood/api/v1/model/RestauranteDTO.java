package com.algafood.api.v1.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import com.algafood.api.v1.openapi.model.RestauranteOpenApiDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(implementation = RestauranteOpenApiDTO.class)
public class RestauranteDTO extends RepresentationModel<RestauranteDTO> {

	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "Brasileirissimo")
	private String nome;
	
	@Schema(example = "9.99")
	private BigDecimal taxaFrete;
	
	private CozinhaDTO cozinha;
	
	@Schema(example = "true")
	private Boolean ativo;
	
	@Schema(example = "false")
	private Boolean aberto;
	
	private EnderecoDTO endereco;
	
}
