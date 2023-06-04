package com.algafood.api.v1.openapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "UsuarioDTO")
public class UsuarioModelOpenApi {

	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "João")
	private String nome;
	
	@Schema(example = "joao@email.com.br")
	private String email;
	
	@Schema(name = "_links")
	private LinksModelOpenApi _links;
}
