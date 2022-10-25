package com.algafood.api.openapi.controller;

import java.util.List;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.model.ProdutoDTO;
import com.algafood.api.model.input.ProdutoInputDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Produtos")
public interface RestauranteProdutoControllerOpenApi {

	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class),
		@ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class)
	})
	@ApiOperation("Lista os produtos de um restaurante")
	List<ProdutoDTO> listar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			@ApiParam(value = "Indica se deve ou não incluir produtos inativos no resultado da listagem", 
			example = "false", defaultValue = "false") boolean incluirInativos);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante ou produto não encontrado", response = Problem.class),
		@ApiResponse(code = 400, message = "ID do restaurante ou produto inválido", response = Problem.class)
	})
	@ApiOperation("Busca um produto de um restaurante")
	ProdutoDTO buscarPorId(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			@ApiParam(value = "ID do produto do restaurante", example = "1", required = true) Long produtoId);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class),
		@ApiResponse(code = 201, message = "Produto cadastrado")
	})
	@ApiOperation("Cadastra um produto de um restaurante")
	ProdutoDTO adicionar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			@ApiParam(value = "Representação de um novo produto", name = "corpo", required = true) ProdutoInputDTO produtoInputDTO);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante ou produto não encontrado", response = Problem.class),
		@ApiResponse(code = 200, message = "Produto atualizado")
	})
	@ApiOperation("Atualiza um produto de um restaurante")
	ProdutoDTO atualizar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			@ApiParam(value = "ID do produto do restaurante", example = "1", required = true) Long produtoId, 
			@ApiParam(value = "Representação do produto com os novos dados", name = "corpo", required = true) ProdutoInputDTO produtoInputDTO);

}