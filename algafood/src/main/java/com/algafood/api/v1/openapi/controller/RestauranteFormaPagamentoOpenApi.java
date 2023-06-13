package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algafood.api.v1.model.FormaPagamentoDTO;
import com.algafood.api.v1.openapi.model.FormasPagamentoModelOpenApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes")
public interface RestauranteFormaPagamentoOpenApi {
	
	@Operation(summary = "Lista as formas de pagamento associadas a um restaurante", responses = {
			@ApiResponse(responseCode = "200", 
					content = @Content(schema = @Schema(implementation = FormasPagamentoModelOpenApi.class))),
			@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", 
            		content = @Content(schema = @Schema(ref = "Problema")))
	})
	CollectionModel<FormaPagamentoDTO> listar(@Parameter(example = "1", description = "ID de um restaurante", 
            required = true)Long restauranteId);

	@Operation(summary = "Desassociação de restaurante com forma de pagamento", responses = {
			@ApiResponse(responseCode = "204", description = "Forma de pagamento desassociada"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", 
			             content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", 
            content = @Content(schema = @Schema(ref = "Problema")))
	})
	ResponseEntity<Void> desassociar(@Parameter(example = "1", description = "ID de um restaurante", 
            required = true) Long restauranteId,
			@Parameter(example = "1", 
			description = "ID de uma forma de pagamento", required = true) Long formaPagamentoId);

	@Operation(summary = "Associação de restaurante com forma de pagamento", responses = {
			@ApiResponse(responseCode = "204", description = "Forma de pagamento associada"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", 
			             content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", 
            content = @Content(schema = @Schema(ref = "Problema")))
	})
	ResponseEntity<Void> associar(@Parameter(example = "1", description = "ID de um restaurante", 
            required = true)Long restauranteId, 
			@Parameter(example = "1", 
			description = "ID de uma forma de pagamento", required = true) Long formaPagamentoId);

}