package com.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.model.EstadoDTO;
import com.algafood.api.model.input.EstadoInputDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {

	@ApiOperation("Lista os estados")
	CollectionModel<EstadoDTO> listar();

	@ApiResponses({
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class),
		@ApiResponse(code = 400, message = "ID do estado inválido", response = Problem.class)
	})
	@ApiOperation("Busca um estado por ID")
	EstadoDTO buscar(@ApiParam(value = "ID de um estado", example = "1", required = true) Long estadoId);

	@ApiResponses({
		@ApiResponse(code = 201, message = "Estado cadastrado")
	})
	@ApiOperation("Cadastra um estado")
	EstadoDTO adicionar(@ApiParam(value = "Representação de um novo estado", 
					name = "corpo", required = true) EstadoInputDTO estadoInputDTO);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class),
		@ApiResponse(code = 200, message = "Estado atualizado")
	})
	@ApiOperation("Atualiza um estado por ID")
	EstadoDTO atualizar(@ApiParam(value = "Representação de um estado com os novos dados", 
			name = "corpo", required = true) EstadoInputDTO estadoInputDTO,
			@ApiParam(value = "ID de um estado", example = "1", required = true) Long estadoId);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class),
		@ApiResponse(code = 204, message = "Estado excluído")
	})
	@ApiOperation("Exclui um estado por ID")
	void excluir(@ApiParam(value = "ID de um estado", example = "1", required = true) Long estadoId);

}