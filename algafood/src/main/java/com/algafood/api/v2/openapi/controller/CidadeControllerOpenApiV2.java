package com.algafood.api.v2.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.v2.model.CidadeDTOV2;
import com.algafood.api.v2.model.input.CidadeInputDTOV2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApiV2 {

	@ApiOperation("Lista as cidades")
	CollectionModel<CidadeDTOV2> listar();

	@ApiResponses({ 
		@ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
	})
	@ApiOperation("Busca uma cidade por ID")
	CidadeDTOV2 buscar(@ApiParam(value = "ID de uma cidade", example = "1", required = true) Long cidadeId);

	@ApiResponses({	
		@ApiResponse(code = 201, message = "Cidade Cadastrada")
	})
	@ApiOperation("Cadastra uma cidade")
	CidadeDTOV2 adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade",
					required = true) CidadeInputDTOV2 cidadeInputDTO);

	@ApiResponses({	
		@ApiResponse(code = 200, message = "Cidade Atualizada"),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
	})
	@ApiOperation("Atualiza uma cidade por ID")
	CidadeDTOV2 atualizar(@ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados", 
		required = true) CidadeInputDTOV2 cidadeInputDTO, @ApiParam(value = "ID de uma cidade", example = "1",
		required = true) Long cidadeId);

	@ApiResponses({	
		@ApiResponse(code = 204, message = "Cidade excluída"),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
	})
	@ApiOperation("Exclui uma cidade por ID")
	void excluir(@ApiParam(value = "ID de uma cidade", example = "1", required = true) Long cidadeId);

}