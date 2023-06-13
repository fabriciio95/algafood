package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.v1.model.ProdutoDTO;
import com.algafood.api.v1.model.input.ProdutoInputDTO;
import com.algafood.api.v1.openapi.model.ProdutosModelOpenApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Produtos")
public interface RestauranteProdutoControllerOpenApi {

	@Operation(summary = "Lista os produtos de um restaurante", responses = {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ProdutosModelOpenApi.class))),
			@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
			        content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", 
	        content = @Content(schema = @Schema(ref = "Problema")))
			
	})
	CollectionModel<ProdutoDTO> listar(@Parameter(example = "1", description = "ID de um restaurante", 
	    required = true) Long restauranteId, @Parameter(example = "true", description = "Indica se os produtos inativos devem ser considerados na busca pelos produtos de um restaurante",
	    required = false) Boolean incluirInativos);

	@Operation(summary = "Busca um produto de um restaurante", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "404", description = "Restaurante ou Produto não encontrado", 
			        content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", 
	        content = @Content(schema = @Schema(ref = "Problema")))
			
	})
	ProdutoDTO buscarPorId(@Parameter(example = "1", description = "ID de um restaurante", 
		    required = true) Long restauranteId, 
			@Parameter(example = "1", description = "ID de um produto", required = true) Long produtoId);

	@Operation(summary = "Busca um produto de um restaurante", responses = {
			@ApiResponse(responseCode = "201"),
			@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
			        content = @Content(schema = @Schema(ref = "Problema")))
			
	})
	ProdutoDTO adicionar(@Parameter(example = "1", description = "ID de um restaurante", 
		    required = true) Long restauranteId,
			@RequestBody(description = "Representaão de um novo produto", required = true) ProdutoInputDTO produtoInputDTO);

	@Operation(summary = "Busca um produto de um restaurante", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "404", description = "Restaurante ou Produto não encontrado", 
			        content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", 
	        content = @Content(schema = @Schema(ref = "Problema")))
			
	})
	ProdutoDTO atualizar(@Parameter(example = "1", description = "ID de um restaurante", 
		    required = true) Long restauranteId, 
			@Parameter(example = "1", description = "ID de um produto", required = true) Long produtoId, 
			@RequestBody(description = "Representaão de um produto com os novos dados", required = true) ProdutoInputDTO produtoInputDTO);

}