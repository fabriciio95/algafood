package com.algafood.api.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.model.FotoProdutoDTO;
import com.algafood.api.model.input.FotoProdutoInputDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Produtos")
public interface RestauranteProdutoFotoControllerOpenApi {

	@ApiResponses({
		@ApiResponse(code = 404, message = "Foto do produto não encontrada", response = Problem.class),
		@ApiResponse(code = 400, message = "ID do restaurante ou produto inválido", response = Problem.class)
	})
	@ApiOperation(value = "Busca a foto do produto de um restaurante", produces = "application/json, image/jpeg, image/png")
	FotoProdutoDTO buscar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId, 
						  @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId);

	@ApiOperation(value = "Busca a foto do produto de um restaurante", hidden = true)
	ResponseEntity<?> servirFoto(Long restauranteId, Long produtoId,  String acceptHeader)
			throws HttpMediaTypeNotAcceptableException;

	@ApiResponses({
		@ApiResponse(code = 404, message = "Produto do restaurante não encontrado", response = Problem.class),
		@ApiResponse(code = 200, message = "Foto do produto atualizada")
	})
	@ApiOperation("Atualiza a foto do produto de um restaurante")
	FotoProdutoDTO atualizarFoto(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId, 
								 @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId, 
								 @ApiParam(value = "Representação da foto do produto", name = "mult", required = true) FotoProdutoInputDTO fotoProdutoInput)
			throws IOException;

	@ApiResponses({
		@ApiResponse(code = 404, message = "Produto do restaurante não encontrado", response = Problem.class),
		@ApiResponse(code = 400, message = "ID do restaurante ou produto inválido", response = Problem.class),
		@ApiResponse(code = 204, message = "Foto do produto Excluída")
	})
	@ApiOperation("Exclui a foto do produto de um restaurante")
	void excluir(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
			     @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId);

}