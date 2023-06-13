package com.algafood.api.v1.openapi.controller;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Pedidos")
public interface FluxoPedidoControllerOpenApi {

	@Operation(summary = "Confirmação de pedido", responses = {
			@ApiResponse(responseCode = "204", description = "Pedido confirmado"),
			@ApiResponse(responseCode = "404", description = "Pedido não encontrado", 
			             content = @Content(schema = @Schema(ref = "Problema")))
	})
	ResponseEntity<Void> confirmar(@Parameter(example = "8f2d22a7-9f3b-421e-9377-4c3b77b34924", 
			description = "Código do pedido", required = true) String codigoPedido);

	@Operation(summary = "Cancelamento de pedido", responses = {
			@ApiResponse(responseCode = "204", description = "Pedido cancelado"),
			@ApiResponse(responseCode = "404", description = "Pedido não encontrado", 
			             content = @Content(schema = @Schema(ref = "Problema")))
	})
	ResponseEntity<Void> cancelar(@Parameter(example = "8f2d22a7-9f3b-421e-9377-4c3b77b34924", 
			description = "Código do pedido", required = true) String codigoPedido);
	
	@Operation(summary = "Registrar entrega de pedido", responses = {
			@ApiResponse(responseCode = "204", description = "Pedido entregue"),
			@ApiResponse(responseCode = "404", description = "Pedido não encontrado", 
			             content = @Content(schema = @Schema(ref = "Problema")))
	})
	ResponseEntity<Void> entregar(@Parameter(example = "8f2d22a7-9f3b-421e-9377-4c3b77b34924", 
			description = "Código do pedido", required = true) String codigoPedido);

}