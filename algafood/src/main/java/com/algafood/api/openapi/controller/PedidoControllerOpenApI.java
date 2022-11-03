package com.algafood.api.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.model.PedidoDTO;
import com.algafood.api.model.PedidoResumoDTO;
import com.algafood.api.model.input.PedidoInputDTO;
import com.algafood.domain.filter.PedidoFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApI {

	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
						  name = "campos", paramType = "query", type = "string")	
	})
	@ApiOperation("Pesquisa os pedidos")
	PagedModel<PedidoResumoDTO> pesquisar(PedidoFilter filtro, Pageable pageable);


	@ApiResponses({
		@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
	})
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
						  name = "campos", paramType = "query", type = "string")	
	})
	@ApiOperation("Busca um pedido por código")
	PedidoDTO buscar(@ApiParam(value = "Código de um pedido", 
							example = "313c828d-5787-4689-aaf4-e94678446682", required = true) String codigoPedido);


	@ApiResponses({
		@ApiResponse(code = 201, message = "Pedido registrado", response = Problem.class)
	})
	@ApiOperation("Registra um pedido")
	PedidoDTO adicionar(@ApiParam(value = "Representação de um novo pedido", name = "corpo", required = true) PedidoInputDTO pedidoInputDTO);

}