package com.algafood.api.v1.openapi.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.v1.model.RestauranteApenasNomeDTO;
import com.algafood.api.v1.model.RestauranteBasicoDTO;
import com.algafood.api.v1.model.RestauranteDTO;
import com.algafood.api.v1.model.input.RestauranteInputDTO;
import com.algafood.api.v1.openapi.model.RestaurantesBasicoModelOpenApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {

	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nome da projeção de restaurantes", allowableValues = "apenas-nome",
						  name = "projecao", paramType = "query", type = "string")
	})
	@ApiOperation(value = "Lista restaurantes", response = RestaurantesBasicoModelOpenApi.class)
	CollectionModel<RestauranteBasicoDTO> listar();

	@ApiIgnore
	@ApiOperation(value = "Lista restaurantes", hidden = true)
	CollectionModel<RestauranteApenasNomeDTO> listarApenasNomes();

	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class),
		@ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class)
	})
	@ApiOperation("Busca um restaurante por ID")
	RestauranteDTO buscar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

	@ApiResponses({
		@ApiResponse(code = 201, message = "Restaurante cadastrado")
	})
	@ApiOperation("Cadastra um restaurante")
	RestauranteDTO adicionar(@ApiParam(name = "corpo", value = "Representação de um novo restaurante", 
				required = true) RestauranteInputDTO restauranteInputDTO);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class),
		@ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
		@ApiResponse(code = 200, message = "Restaurante atualizado")
	})
	@ApiOperation("Atualiza um restaurante por ID")
	RestauranteDTO atualizar(@ApiParam(name = "corpo", value = "Representação de um restaurante com os novos dados", 
			required = true) RestauranteInputDTO restauranteInputDTO, 
			@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class),
		@ApiResponse(code = 204, message = "Restaurante ativado com sucesso"),
		@ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class)
	})
	@ApiOperation("Ativa um restaurante por ID")
	ResponseEntity<Void> ativar(@ApiParam(example = "1", required = true, value = "ID de um restaurante") Long restauranteId);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class),
		@ApiResponse(code = 204, message = "Restaurante inativado com sucesso"),
		@ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class)
	})
	@ApiOperation("Inativa um restaurante por ID")
	ResponseEntity<Void> inativar(@ApiParam(example = "1", required = true, value = "ID de um restaurante") Long restauranteId);

	@ApiResponses({
	        @ApiResponse(code = 204, message = "Restaurantes ativados com sucesso")
	   	})
	@ApiOperation("Ativa múltiplos restaurantes")
	void ativarMultiplos(@ApiParam(required = true, value = "IDs de restaurantes") List<Long> restauranteIds);

	@ApiResponses({
	        @ApiResponse(code = 204, message = "Restaurantes inativados com sucesso")
	    })
	@ApiOperation("Inativa múltiplos restaurantes")
	void inativarMultiplos(@ApiParam(required = true, value = "IDs de restaurantes") List<Long> restauranteIds);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class),
		@ApiResponse(code = 204, message = "Restaurante fechado com sucesso"),
		@ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class)
	})
	@ApiOperation("Fecha um restaurante por ID")
	ResponseEntity<Void> fechar(@ApiParam(example = "1", required = true, value = "ID de um restaurante") Long restauranteId);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class),
		@ApiResponse(code = 204, message = "Restaurante aberto com sucesso"),
		@ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class)
	})
	@ApiOperation("Abre um restaurante por ID")
	ResponseEntity<Void> abrir(@ApiParam(example = "1", required = true, value = "ID de um restaurante") Long restauranteId);

	@ApiImplicitParams({
		@ApiImplicitParam(value = "Representação dos campos com os novos dados de um restaurante",
						  name = "corpo", paramType = "body", 
						  dataType = "RestauranteInputModel", required = true)
	})
	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class),
		@ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class)
	})
	@ApiOperation("Atualiza apenas os campos informados de um restaurante por ID")
	RestauranteDTO atualizarParcial(
				@ApiParam(hidden = true) Map<String, Object> campos, 
				@ApiParam(example = "1", required = true, value = "ID de um restaurante") Long restauranteId, HttpServletRequest request);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class),
		@ApiResponse(code = 204, message = "Restaurante excluído com sucesso"),
		@ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class)
	})
	@ApiOperation("Exclui um restaurante por ID")
	void excluir(@ApiParam(example = "1", required = true, value = "ID de um restaurante") Long restauranteId);

}