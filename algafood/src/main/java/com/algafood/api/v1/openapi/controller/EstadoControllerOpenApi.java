package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.v1.model.EstadoDTO;
import com.algafood.api.v1.model.input.EstadoInputDTO;
import com.algafood.api.v1.openapi.model.EstadosModelOpenApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Estados")
public interface EstadoControllerOpenApi {

	@Operation(summary = "Lista os estados", responses = {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = EstadosModelOpenApi.class)))
	})
	CollectionModel<EstadoDTO> listar();

	@Operation(summary = "Busca um estado por ID", responses =  {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "ID do estado inválido", 
			             content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Estado não encontrado", 
			             content = @Content(schema = @Schema(ref = "Problema")))
	})
	EstadoDTO buscar(@Parameter(example = "1", description = "ID de um estado", required = true) Long estadoId);

	@Operation(summary = "Cadastra um estado", responses =  {
			@ApiResponse(responseCode = "201", description = "Estado cadastrado")
	})
	EstadoDTO adicionar(@RequestBody(description = "Representação de um novo estado", required = true) EstadoInputDTO estadoInputDTO);

	@Operation(summary = "Atualiza um estado por ID", responses =  {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "ID do estado inválido", 
			             content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Estado não encontrado", 
			             content = @Content(schema = @Schema(ref = "Problema")))
	})
	EstadoDTO atualizar(@RequestBody(description = "Representação de um estado com os novos dados", required = true)EstadoInputDTO estadoInputDTO,
			@Parameter(example = "1", description = "ID de um estado", required = true) Long estadoId);

	@Operation(summary = "Exclui um estado por ID", responses =  {
			@ApiResponse(responseCode = "204"),
			@ApiResponse(responseCode = "400", description = "ID do estado inválido", 
			             content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Estado não encontrado", 
			             content = @Content(schema = @Schema(ref = "Problema")))
	})
	void excluir(@Parameter(example = "1", description = "ID de um estado", required = true) Long estadoId);

}