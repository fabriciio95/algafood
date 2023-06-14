package com.algafood.api.v1.openapi.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import com.algafood.api.v1.model.FotoProdutoDTO;
import com.algafood.api.v1.model.input.FotoProdutoInputDTO;

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
public interface RestauranteProdutoFotoControllerOpenApi {

	@Operation(summary = "Busca foto do produto de um restaurante", responses = {
			@ApiResponse(responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = FotoProdutoDTO.class)),
					@Content(mediaType = "image/jpeg", schema = @Schema(type = "string", format = "binary")),
					@Content(mediaType = "image/png", schema = @Schema(type = "string", format = "binary"))
			}),
			@ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado", 
			        content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", 
	        content = @Content(schema = @Schema(ref = "Problema")))
	})
	FotoProdutoDTO buscar(@Parameter(example = "1", description = "ID de um restaurante", 
		    required = true)Long restauranteId, 
			@Parameter(example = "1", description = "ID de um produto", required = true) Long produtoId);

	@Operation(hidden = true)
	ResponseEntity<?> servirFoto(Long restauranteId, Long produtoId,  String acceptHeader)
			throws HttpMediaTypeNotAcceptableException;

	@Operation(summary = "Atualiza a foto do produto de um restaurante", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida",
			             content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado", 
			             content = @Content(schema = @Schema(ref = "Problema")))
	})
	FotoProdutoDTO atualizarFoto(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
								 @Parameter(description = "ID do produto", example = "1", required = true)Long produtoId, 
			                     @RequestBody(required = true) FotoProdutoInputDTO fotoProdutoInput)
			throws IOException;

	
	@Operation(summary = "Exclui uma foto de um produto de um restaurante por ID", responses =  {
			@ApiResponse(responseCode = "204", description = "Foto excluída"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida",
			             content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado", 
			             content = @Content(schema = @Schema(ref = "Problema")))
	})
	void excluir(@Parameter(example = "1", description = "ID de um restaurante", 
		    required = true) Long restauranteId, 
			@Parameter(example = "1", description = "ID de um produto", required = true) Long produtoId);

}