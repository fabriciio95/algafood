package com.algafood.api.v1.openapi.controller;

import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algafood.api.v1.model.RestauranteApenasNomeDTO;
import com.algafood.api.v1.model.RestauranteBasicoDTO;
import com.algafood.api.v1.model.RestauranteDTO;
import com.algafood.api.v1.model.input.RestauranteInputDTO;
import com.algafood.api.v1.openapi.model.RestaurantesModelOpenApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes")
public interface RestauranteControllerOpenApi {

	@Operation(summary = "Lista restaurantes", parameters = {
		@Parameter(name = "projecao", 
				   description = "Nome da projeção",
				   example = "apenas-nome",
				   in = ParameterIn.QUERY,
				   required = false)
	}, responses = {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = RestaurantesModelOpenApi.class)))
	})
	CollectionModel<RestauranteBasicoDTO> listar();

	@Operation(hidden = true)
	CollectionModel<RestauranteApenasNomeDTO> listarApenasNomes();

	@Operation(summary = "Busca um restaurante por ID", responses =  {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "ID do restaurante inválido",
			             content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
			             content = @Content(schema = @Schema(ref = "Problema")))
	})
	RestauranteDTO buscar(@Parameter(example = "1", description = "ID de um restaurante", 
	                      required = true) Long restauranteId);

	@Operation(summary = "Cadastra um restaurante por ID", responses =  {
			@ApiResponse(responseCode = "201", description = "Restaurante Cadastrado")
	})
	RestauranteDTO adicionar(@RequestBody(description = "Representação de um novo restaurante", required = true) RestauranteInputDTO restauranteInputDTO);

	@Operation(summary = "Atualiza um restaurante por ID", responses =  {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "ID do restaurante inválido",
			             content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
			             content = @Content(schema = @Schema(ref = "Problema")))
	})
	RestauranteDTO atualizar(@RequestBody(description = "Representação de um restaurante com os novos dados", required = true) RestauranteInputDTO restauranteInputDTO,
			@Parameter(example = "1", description = "ID de um restaurante", 
            	required = true) Long restauranteId);

	@Operation(summary = "Ativa um restaurante por ID", responses =  {
			@ApiResponse(responseCode = "204", description = "Restaurante ativado"),
			@ApiResponse(responseCode = "400", description = "ID do restaurante inválido",
			             content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
			             content = @Content(schema = @Schema(ref = "Problema")))
	})
	ResponseEntity<Void> ativar(@Parameter(example = "1", description = "ID de um restaurante", 
            required = true) Long restauranteId);

	@Operation(summary = "Desativa um restaurante por ID", responses =  {
			@ApiResponse(responseCode = "204", description = "Restaurante desativado"),
			@ApiResponse(responseCode = "400", description = "ID do restaurante inválido",
			             content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
			             content = @Content(schema = @Schema(ref = "Problema")))
	})
	ResponseEntity<Void> inativar(@Parameter(example = "1", description = "ID de um restaurante", 
            required = true) Long restauranteId);

	
	@Operation(summary = "inativa múltiplos restaurantes", responses =  {
			@ApiResponse(responseCode = "204", description = "Restaurantes desativados"),
			@ApiResponse(responseCode = "400", description = "ID do restaurante inválido",
			             content = @Content(schema = @Schema(ref = "Problema")))
	})
	void ativarMultiplos(@RequestBody(description = "Lista com os IDs de restaurantes", 
            required = true) List<Long> restauranteIds);

	@Operation(summary = "Ativa múltiplos restaurantes", responses =  {
			@ApiResponse(responseCode = "204", description = "Restaurantes desativados"),
			@ApiResponse(responseCode = "400", description = "ID do restaurante inválido",
			             content = @Content(schema = @Schema(ref = "Problema")))
	})
	void inativarMultiplos(@RequestBody(description = "Lista com os IDs de restaurantes", 
            required = true) List<Long> restauranteIds);

	
	@Operation(summary = "Fecha um restaurante por ID", responses =  {
			@ApiResponse(responseCode = "204", description = "Restaurante fechado"),
			@ApiResponse(responseCode = "400", description = "ID do restaurante inválido",
			             content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
			             content = @Content(schema = @Schema(ref = "Problema")))
	})
	ResponseEntity<Void> fechar(@Parameter(example = "1", description = "ID de um restaurante", 
            required = true)Long restauranteId);

	@Operation(summary = "Abre um restaurante por ID", responses =  {
			@ApiResponse(responseCode = "204", description = "Restaurante aberto"),
			@ApiResponse(responseCode = "400", description = "ID do restaurante inválido",
			             content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
			             content = @Content(schema = @Schema(ref = "Problema")))
	})
	ResponseEntity<Void> abrir(@Parameter(example = "1", description = "ID de um restaurante", 
            required = true)Long restauranteId);

	@Operation(summary = "Fecha um restaurante por ID", responses =  {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "ID do restaurante inválido",
			             content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
			             content = @Content(schema = @Schema(ref = "Problema")))
	})
	RestauranteDTO atualizarParcial(@RequestBody(description = "Representação de um restaurante apenas com os campos que serão atualizados",
	         required = true, content = @Content(schema = @Schema(implementation = RestauranteInputDTO.class))) Map<String, Object> campos,
			@Parameter(example = "1", description = "ID de um restaurante", 
            required = true) Long restauranteId, @Parameter(hidden = true) HttpServletRequest request);

	@Operation(summary = "Exclui um restaurante por ID", responses =  {
			@ApiResponse(responseCode = "204", description = "Restaurante excluído"),
			@ApiResponse(responseCode = "400", description = "ID do restaurante inválido",
			             content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
			             content = @Content(schema = @Schema(ref = "Problema")))
	})
	void excluir(@Parameter(example = "1", description = "ID de um restaurante", 
            required = true) Long restauranteId);

}