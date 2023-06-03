package com.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algafood.api.v1.model.CozinhaDTO;
import com.algafood.api.v1.model.input.CozinhaInputDTO;
import com.algafood.core.springdoc.PageableParameter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cozinhas")
public interface CozinhaControllerOpenApi {
	
	@PageableParameter
	@Operation(summary = "Lista as cozinhas")
	PagedModel<CozinhaDTO> listar(@Parameter(hidden = true) Pageable pageable);

	@Operation(summary = "Busca uma cozinha por ID", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "404", description = "Cozinha não encontrada", 
				content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", 
				content = @Content(schema = @Schema(ref = "Problema")))
	})
	CozinhaDTO buscar(@Parameter(example = "1", description = "ID de uma cozinha", required = true) Long cozinhaId);

	@Operation(summary = "Cadastra uma cozinha", responses = {
			@ApiResponse(responseCode = "201", description = "Cozinha cadastrada")
	})
	CozinhaDTO adicionar(@RequestBody(description = "Representação de uma nova cozinha", required = true) CozinhaInputDTO cozinhaInputDTO);

	@Operation(summary = "Atualiza uma cozinha por ID", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "404", description = "Cozinha não encontrada", 
				content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", 
				content = @Content(schema = @Schema(ref = "Problema")))
	})
	CozinhaDTO atualizar(@Parameter(example = "1", description = "ID de uma cozinha", required = true) Long cozinhaId, 
			@RequestBody(description = "Representação de uma cozinha existente com os dados atualizados", required = true) CozinhaInputDTO cozinhaInputDTO);

	@Operation(summary = "Exclui uma cozinha por ID", responses = {
			@ApiResponse(responseCode = "204"),
			@ApiResponse(responseCode = "404", description = "Cozinha não encontrada", 
				content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", 
				content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "409", description = "A solicitação não pôde ser concluída devido a um conflito com o estado atual do recurso de destino", 
			content = @Content(schema = @Schema(ref = "Problema")))
	})
	void excluir(@Parameter(example = "1", description = "ID de uma cozinha", required = true) Long cozinhaId);

}