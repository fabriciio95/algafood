package com.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algafood.api.v1.model.PedidoDTO;
import com.algafood.api.v1.model.PedidoResumoDTO;
import com.algafood.api.v1.model.input.PedidoInputDTO;
import com.algafood.api.v1.openapi.model.PedidosResumoModelOpenApi;
import com.algafood.core.springdoc.PageableParameter;
import com.algafood.domain.filter.PedidoFilter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Pedidos")
public interface PedidoControllerOpenApI {

	@PageableParameter
	@Operation(summary = "Pesquisa os pedidos", 
	        responses = {
	        	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PedidosResumoModelOpenApi.class)))},
			parameters = {
				@Parameter(
						in = ParameterIn.QUERY,
						name = "clienteId",
						description = "ID do cliente para filtro de pesquisa",
						schema = @Schema(type = "integer", example = "1")
				),
				@Parameter(
						in = ParameterIn.QUERY,
						name = "restauranteId",
						description = "ID do restaurante para filtro de pesquisa",
						schema = @Schema(type = "integer", example = "1")
				),
				@Parameter(
						in = ParameterIn.QUERY,
						name = "dataCriacaoInicio",
						description = "Data/hora de criação inicial para filtro de pesquisa",
						example = "2019-12-01T00:00:00Z", schema = @Schema(type = "string", format = "date-time")
				),
				@Parameter(
						in = ParameterIn.QUERY,
						name = "dataCriacaoFim",
						description = "Data/hora de criação final para filtro de pesquisa",
						example = "2019-12-02T23:59:59Z", schema = @Schema(type = "string", format = "date-time")
				)}
		
	)
	PagedModel<PedidoResumoDTO> pesquisar(@Parameter(hidden = true) PedidoFilter filtro, 
			@Parameter(hidden = true) Pageable pageable);

	@Operation(summary = "Busca um pedido por código", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "404", description = "Pedido não encontrado", 
				content = @Content(schema = @Schema(ref = "Problema")))
	})
	PedidoDTO buscar(@Parameter(example = "8f2d22a7-9f3b-421e-9377-4c3b77b34924", 
			description = "Código de um pedido", required = true) String codigoPedido);

	@Operation(summary = "Registra um pedido", responses = {
			@ApiResponse(responseCode = "201", description = "Pedido registrado")
	})
	PedidoDTO adicionar(@RequestBody(description = "Representação de novo um pedido", 
		required = true) PedidoInputDTO pedidoInputDTO);

}