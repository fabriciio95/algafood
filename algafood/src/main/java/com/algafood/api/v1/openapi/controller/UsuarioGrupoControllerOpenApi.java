package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algafood.api.v1.model.GrupoDTO;
import com.algafood.api.v1.openapi.model.GruposModelOpenApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Usuários")
public interface UsuarioGrupoControllerOpenApi {

	@Operation(summary = "Lista os grupos associados a um usuário", responses = {
			@ApiResponse(responseCode = "200", 
					content = @Content(schema = @Schema(implementation = GruposModelOpenApi.class))),
			@ApiResponse(responseCode = "400", description = "ID do usuário inválido", 
					content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado", 
			content = @Content(schema = @Schema(ref = "Problema")))
	})
	CollectionModel<GrupoDTO> listar(@Parameter(example = "1", description = "ID de um usuário", 
			required = true) Long usuarioId);

	@Operation(summary = "Desassociação de grupo com usuário", responses = {
			@ApiResponse(responseCode = "204", description = "Grupo desassociado"),
			@ApiResponse(responseCode = "400", description = "ID do usuário ou grupo inválido", 
					content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado", 
			content = @Content(schema = @Schema(ref = "Problema")))
	})
	ResponseEntity<Void> desasociarGrupo(@Parameter(example = "1", description = "ID de um usuário", 
				required = true) Long usuarioId, @Parameter(example = "1", 
						description = "ID de um grupo", required = true) Long grupoId);

	@Operation(summary = "Associação de grupo com usuário", responses = {
			@ApiResponse(responseCode = "204", description = "Grupo associado"),
			@ApiResponse(responseCode = "400", description = "ID do usuário ou grupo inválido", 
					content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado", 
			content = @Content(schema = @Schema(ref = "Problema")))
	})
	ResponseEntity<Void> asociarGrupo(@Parameter(example = "1", description = "ID de um usuário", 
			required = true) Long usuarioId, @Parameter(example = "1", 
			description = "ID de um grupo", required = true) Long grupoId);

}