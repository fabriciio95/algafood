package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algafood.api.v1.model.UsuarioDTO;
import com.algafood.api.v1.openapi.model.UsuariosModelOpenApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes")
public interface RestauranteUsuarioControllerOpenApi {

	@Operation(summary = "Lista os usuários responsáveis associadas a um restaurante", responses = {
			@ApiResponse(responseCode = "200", 
					content = @Content(schema = @Schema(implementation = UsuariosModelOpenApi.class))),
			@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
            		content = @Content(schema = @Schema(ref = "Problema")))
	})
	CollectionModel<UsuarioDTO> listar(@Parameter(example = "1", description = "ID de um restaurante", 
            required = true) Long restauranteId);

	@Operation(summary = "Associação de restaurante com usuário responsável", responses = {
			@ApiResponse(responseCode = "204", description = "Usuário responsável associado"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", 
			             content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Restaurante ou usuário não encontrado", 
            content = @Content(schema = @Schema(ref = "Problema")))
	})
	ResponseEntity<Void> associarResponsavel(@Parameter(example = "1", description = "ID de um restaurante", 
            required = true) Long restauranteId, @Parameter(example = "1",
            description = "ID de um usuário", required = true) Long usuarioId);

	@Operation(summary = "Desassociação de restaurante com usuário responsável", responses = {
			@ApiResponse(responseCode = "204", description = "Usuário responsável desassociado"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", 
			             content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Restaurante ou usuário não encontrado", 
            content = @Content(schema = @Schema(ref = "Problema")))
	})
	ResponseEntity<Void> deassociarResponsavel(@Parameter(example = "1", description = "ID de um restaurante", 
            required = true) Long restauranteId, @Parameter(example = "1",
            description = "ID de um usuário", required = true) Long usuarioId);

}