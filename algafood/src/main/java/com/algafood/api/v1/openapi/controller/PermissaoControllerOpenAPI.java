package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.v1.model.PermissaoDTO;
import com.algafood.api.v1.openapi.model.PermissoesModelOpenApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Permissões")
public interface PermissaoControllerOpenAPI {

	@Operation(summary = "Lista as permissões", responses = {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PermissoesModelOpenApi.class)))
	})
	CollectionModel<PermissaoDTO> listar();

}