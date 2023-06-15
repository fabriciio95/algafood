package com.algafood.api.v1.openapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "PermissaoDTO")
public class PermissaoDTOOpenApi {

	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "CONSULTAR_COZINHAS")
	private String nome;
	
	@Schema(example = "Permite consultar cozinhas")
	private String descricao;
	
	@Schema(name = "_links")
	private LinksModelOpenApi _links;
}
