package com.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.model.CidadeDTO;
import com.algafood.api.model.input.CidadeInputDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

	@ApiOperation("Lista as cidades")
	CollectionModel<CidadeDTO> listar();

	@ApiResponses({ 
		@ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
	})
	@ApiOperation("Busca uma cidade por ID")
	CidadeDTO buscar(@ApiParam(value = "ID de uma cidade", example = "1", required = true) Long cidadeId);

	@ApiResponses({	
		@ApiResponse(code = 201, message = "Cidade Cadastrada")
	})
	@ApiOperation("Cadastra uma cidade")
	CidadeDTO adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade",
					required = true) CidadeInputDTO cidadeInputDTO);

	@ApiResponses({	
		@ApiResponse(code = 200, message = "Cidade Atualizada"),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
	})
	@ApiOperation("Atualiza uma cidade por ID")
	CidadeDTO atualizar(@ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados", 
		required = true) CidadeInputDTO cidadeInputDTO, @ApiParam(value = "ID de uma cidade", example = "1",
		required = true) Long cidadeId);

	@ApiResponses({	
		@ApiResponse(code = 204, message = "Cidade excluída"),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
	})
	@ApiOperation("Exclui uma cidade por ID")
	void excluir(@ApiParam(value = "ID de uma cidade", example = "1", required = true) Long cidadeId);

}