package com.algafood.api.v1.openapi.model;

import java.util.List;

import com.algafood.api.v1.model.PermissaoDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "PermissoesModel")
public class PermissoesModelOpenApi {
	
	@Schema(name = "_embedded")
	private PermissoesEmbeddedModelOpenApi _embedded;
	
	@Schema(name = "_links")
	private LinksModelOpenApi _links;
 
	@Data
	@Schema(name = "PermissoesEmbeddedModel")
	public class PermissoesEmbeddedModelOpenApi {
		
		List<PermissaoDTO> permissoes;
	}
}
