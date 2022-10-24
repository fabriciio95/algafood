package com.algafood.api.openapi.controller;

import com.algafood.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface FluxoPedidoControllerOpenApi {

	@ApiResponses({
		@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class),
		@ApiResponse(code = 204, message = "Pedido confirmado com sucesso")
	})
	@ApiOperation("Confirmação de pedido")
	void confirmar(@ApiParam(value = "Código do pedido", 
							example = "313c828d-5787-4689-aaf4-e94678446682", required = true) String codigoPedido);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class),
		@ApiResponse(code = 204, message = "Pedido cancelado com sucesso")
	})
	@ApiOperation("Cancelamento do pedido")
	void cancelar(@ApiParam(value = "Código do pedido", 
			example = "313c828d-5787-4689-aaf4-e94678446682", required = true) String codigoPedido);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class),
		@ApiResponse(code = 204, message = "Entrega de pedido registrada com sucesso")
	})
	@ApiOperation("Registrar entrega de pedido")
	void entregar(@ApiParam(value = "Código do pedido", 
			example = "313c828d-5787-4689-aaf4-e94678446682", required = true) String codigoPedido);

}