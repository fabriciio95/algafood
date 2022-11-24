package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.v1.model.FormaPagamentoDTO;
import com.algafood.api.v1.model.input.FormaPagamentoInputDTO;
import com.algafood.api.v1.openapi.model.FormasPagamentoModelOpenApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Formas de Pagamento")
public interface FormaPagamentoControllerOpenApi {

	@ApiOperation(value = "Lista as formas de pagamentos", response = FormasPagamentoModelOpenApi.class)
	ResponseEntity<CollectionModel<FormaPagamentoDTO>> listar(ServletWebRequest request);

	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da forma de pagamento inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
	})
	@ApiOperation("Busca uma forma de pagamento por ID")
	ResponseEntity<FormaPagamentoDTO> buscar(@ApiParam(value = "ID de uma forma de pagamento", 
				example = "1", required = true) Long formaPagamentoId, ServletWebRequest request);

	@ApiResponses({
		@ApiResponse(code = 201, message = "Forma de pagamento cadastrada")
	})
	@ApiOperation("Cadastra uma forma de pagamento")
	FormaPagamentoDTO adicionar(@ApiParam(name = "corpo", 
			value = "Representação de uma nova forma de pagamento", required = true) FormaPagamentoInputDTO formaPagamentoInputDTO);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class),
		@ApiResponse(code = 200, message = "Forma de pagamento atualizada")
	})
	@ApiOperation("Atualiza uma forma de pagamento por ID")
	FormaPagamentoDTO atualizar(@ApiParam(value = "ID de uma forma de pagamento",
			example = "1", required = true) Long formaPagamentoId, 
			@ApiParam(name = "corpo", required = true,
			value = "Representação de uma forma de pagamento com os novos dados") FormaPagamentoInputDTO formaPagamentoInputDTO);

	@ApiResponses({
		@ApiResponse(code = 204, message = "Forma de pagamento excluída"),
		@ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
	})
	@ApiOperation("Exclui uma forma de pagamento por ID")
	void excluir(@ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId);

}