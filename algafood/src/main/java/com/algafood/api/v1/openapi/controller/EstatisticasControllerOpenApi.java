package com.algafood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.algafood.api.v1.controller.EstatisticasController.EstatisticasModel;
import com.algafood.domain.filter.VendaDiariaFilter;
import com.algafood.domain.model.dto.VendaDiaria;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Estatísticas")
public interface EstatisticasControllerOpenApi {
	
	@Operation(summary = "Exibe os links para vendas diárias")
	EstatisticasModel estatisticas();

	@Operation(summary = "Consulta estatísticas de vendas diárias", responses = {
			@ApiResponse(responseCode = "200", content = {
					@Content(mediaType = "application/json", array =  @ArraySchema(schema = @Schema(implementation = VendaDiaria.class))),
					@Content(mediaType = "application/pdf", schema = @Schema(type = "string", format = "binary")),
			})
	}, parameters = {
			@Parameter(
					in = ParameterIn.QUERY,
					name = "restauranteId",
					description = "ID de um restaurante",
					schema = @Schema(type = "integer", example = "1")
			), 
			@Parameter(
					in = ParameterIn.QUERY,
					name = "dataCriacaoInicio",
					description = "Data/hora de criação inicial para filtro de pesquisa",
					example = "2019-12-01T00:00:00Z", schema = @Schema(type = "string", format = "date-time")
			),
			@Parameter(
					in = ParameterIn.QUERY,
					name = "dataCriacaoFim",
					description = "Data/hora de criação final para filtro de pesquisa",
					example = "2019-12-02T23:59:59Z", schema = @Schema(type = "string", format = "date-time")
			)
	})
	List<VendaDiaria> consultarVendasDiarias(@Parameter(hidden = true) VendaDiariaFilter filtro,
			@Parameter(description = "Deslocamento de horário a ser considerado na consulta em relação ao UTC", 
			   required = false, schema = @Schema(type = "string", defaultValue = "+00:00")) String timeOffset);

	@Operation(hidden = true)
	ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro, String timeOffset);

}