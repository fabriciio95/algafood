package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algafood.api.v1.model.PermissaoDTO;
import com.algafood.api.v1.openapi.model.PermissoesModelOpenApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grupos")
public interface GrupoPermissaoControllerOpenApi {
	
	@Operation(summary = "Lista as permissões associadas a um grupo", responses =  {
			@ApiResponse(responseCode = "200", 
					content = @Content(schema = @Schema(implementation = PermissoesModelOpenApi.class))),
			@ApiResponse(responseCode = "400", description = "ID do grupo inválido",
			        content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Grupo não encontrado",
			        content = @Content(schema = @Schema(ref = "Problema")) )
	})
	CollectionModel<PermissaoDTO> listar(@Parameter(example = "1", description = "ID de um grupo", required = true) Long grupoId);

	@Operation(summary = "Desassociação de permissão com grupo", responses =  {
			@ApiResponse(responseCode = "204", description = "Permissão desassociada"),
			@ApiResponse(responseCode = "400", description = "ID do grupo ou permissão inválido",
			        content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Grupo ou permissão não encontrado",
			        content = @Content(schema = @Schema(ref = "Problema")) )
	})
	ResponseEntity<Void> desassociar(@Parameter(example = "1", description = "ID de um grupo", required = true) Long grupoId, 
			@Parameter(example = "1", description = "ID de uma permissão", required = true) Long permissaoId);

	@Operation(summary = "Associação de permissão com grupo", responses =  {
			@ApiResponse(responseCode = "204", description = "Permissão associada"),
			@ApiResponse(responseCode = "400", description = "ID do grupo ou permissão inválido",
			        content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Grupo ou permissão não encontrado",
			        content = @Content(schema = @Schema(ref = "Problema")) )
	})
	ResponseEntity<Void> associar(@Parameter(example = "1", description = "ID de um grupo", required = true) Long grupoId,
			@Parameter(example = "1", description = "ID de uma associação", required = true) Long permissaoId);

}