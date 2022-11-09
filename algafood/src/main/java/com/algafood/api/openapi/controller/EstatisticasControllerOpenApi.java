package com.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.algafood.domain.filter.VendaDiariaFilter;
import com.algafood.domain.model.dto.VendaDiaria;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Estatísticas")
public interface EstatisticasControllerOpenApi {

	@ApiImplicitParams({
		@ApiImplicitParam(value = "Data/hora final da criação do pedido", name = "dataCriacaoFim", 
				example = "2019-12-02T23:59:59Z", dataType = "date-time"),
		@ApiImplicitParam(value = "Data/hora inicial da criação do pedido", name = "dataCriacaoInicio",
				example = "2019-11-02T00:00:00Z", dataType = "date-time"),
		@ApiImplicitParam(value = "ID do restaurante", name = "restauranteId", example = "1", dataType = "int")
	})
	@ApiOperation("Consulta estatísticas de vendas diárias")
	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro,
				@ApiParam(value = "Deslocamento de horário a ser considerado na consulta em relação ao UTC", 
						 defaultValue = "+00:00") String timeOffset);

	ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro, String timeOffset);

}