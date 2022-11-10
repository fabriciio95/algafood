package com.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.model.FormaPagamentoDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteFormaPagamentoOpenApi {
	
	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	})
	@ApiOperation("Lista as formas de pagamento associados a restaurante")
	CollectionModel<FormaPagamentoDTO> listar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado", response = Problem.class),
		@ApiResponse(code = 204, message = "Desassociação realizada com sucesso")
	})
	@ApiOperation("Desassociação de restaurante com forma de pagamento")
	ResponseEntity<Void> desassociar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId, 
			@ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado", response = Problem.class),
		@ApiResponse(code = 204, message = "Associação realizada com sucesso")
	})
	@ApiOperation("Associação de restaurante com forma de pagamento")
	ResponseEntity<Void> associar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId, 
			@ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId);

}