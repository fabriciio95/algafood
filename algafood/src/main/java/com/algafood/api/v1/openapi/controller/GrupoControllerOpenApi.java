package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.v1.model.GrupoDTO;
import com.algafood.api.v1.model.input.GrupoInputDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grupos")
public interface GrupoControllerOpenApi {

	@Operation(summary = "Lista os grupos")
	CollectionModel<GrupoDTO> listar();

	@Operation(summary = "Busca um grupo por ID", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "404", 
						 description = "Grupo não encontrado", 
			             content = @Content(schema = @Schema(ref = "Problema")))
	})
	GrupoDTO buscarPorId(@Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId);

	@Operation(summary = "Cadastra um Grupo", responses = {
			@ApiResponse(responseCode = "201")
	})
	GrupoDTO adicionar(@RequestBody(description = "Representação de um novo grupo", required = true) GrupoInputDTO grupoInputDTO);

	@Operation(summary = "Atualiza um grupo por ID", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "404", 
						 description = "Grupo não encontrado", 
			             content = @Content(schema = @Schema(ref = "Problema")))
	})
	GrupoDTO atualizar(@RequestBody(description = "Representação de um grupo com os dados atualizados", 
									required = true) GrupoInputDTO grupoInputDTO, 
			           @Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId);

	@Operation(summary = "Exclui um grupo por ID", responses = {
			@ApiResponse(responseCode = "204"),
			@ApiResponse(responseCode = "404", 
						 description = "Grupo não encontrado", 
			             content = @Content(schema = @Schema(ref = "Problema")))
	})
	void excluir(@Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId);

}