package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.algafood.api.v1.model.FormaPagamentoDTO;
import com.algafood.api.v1.model.input.FormaPagamentoInputDTO;
import com.algafood.api.v1.openapi.model.FormasPagamentoModelOpenApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Formas de Pagamento")
public interface FormaPagamentoControllerOpenApi {

	@Operation(summary = "Lista as formas de pagamento", responses = {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = FormasPagamentoModelOpenApi.class)))
	})
	ResponseEntity<CollectionModel<FormaPagamentoDTO>> listar(@Parameter(hidden = true) ServletWebRequest request);

	@Operation(summary = "Busca uma forma de pagamento por ID", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "404", description = "Forma de Pagamento não encontrada",
					content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", 
					content = @Content(schema = @Schema(ref = "Problema")))
	})
	ResponseEntity<FormaPagamentoDTO> buscar(@Parameter(example = "1", 
			description = "ID de uma forma de pagamento", required = true) Long formaPagamentoId,
			@Parameter(hidden = true) ServletWebRequest request);

	@Operation(summary = "Cadastra uma forma de pagamento", responses = {
			@ApiResponse(responseCode = "201", description = "Forma de pagamento cadastrada")
	})
	FormaPagamentoDTO adicionar(@RequestBody(description = "Representação de uma forma de pagamento", 
				required = true) FormaPagamentoInputDTO formaPagamentoInputDTO);

	@Operation(summary = "Atualiza uma forma de pagamento por ID", responses = {
			@ApiResponse(responseCode = "200", description = "Forma de pagamento atualizada"),
			@ApiResponse(responseCode = "404", description = "Forma de Pagamento não encontrada",
			content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", 
			content = @Content(schema = @Schema(ref = "Problema")))
	})
	FormaPagamentoDTO atualizar(@Parameter(example = "1", description = "ID de uma forma de pagamento", 
			required = true) Long formaPagamentoId, @RequestBody(description = "Representação de uma forma de pagamento",
			required = true) FormaPagamentoInputDTO formaPagamentoInputDTO);

	@Operation(summary = "Exclui uma forma de pagamento por ID", responses = {
			@ApiResponse(responseCode = "204", description = "Forma de pagamento excluida"),
			@ApiResponse(responseCode = "404", description = "Forma de Pagamento não encontrada",
			content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", 
			content = @Content(schema = @Schema(ref = "Problema")))
	})
	void excluir(@Parameter(example = "1", description = "ID de uma forma de pagamento", 
			required = true) Long formaPagamentoId);

}