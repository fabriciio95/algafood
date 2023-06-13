package com.algafood.api.v1.openapi.model;

import java.util.List;

import com.algafood.api.v1.model.UsuarioDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "UsuariosModelDTO")
public class UsuariosModelOpenApi {
	
	@Schema(name = "_embedded")
	private UsuariosEmbeddedModelOpenApi _embedded;
	
	@Schema(name = "_links")
	private LinksModelOpenApi _links;

	
	@Data
	@Schema(name = "UsuariosEmbeddedDTO")
	public class UsuariosEmbeddedModelOpenApi {
		
		List<UsuarioDTO> usuarios;
	}
}
