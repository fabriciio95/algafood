package com.algafood.api.v1.openapi.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algafood.api.v1.model.RestauranteApenasNomeDTO;
import com.algafood.api.v1.model.UsuarioDTO;
import com.algafood.domain.model.StatusPedido;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "PedidoResumoDTO")
public class PedidoResumoModelOpenApi {

	@Schema(example = "8f2d22a7-9f3b-421e-9377-4c3b77b34924")
	private String codigo;
	
	@Schema(example = "22.55")
	private BigDecimal subtotal;
	
	@Schema(example = "9.50")
	private BigDecimal taxaFrete;
	
	@Schema(example = "32.5")
	private BigDecimal valorTotal;
	
	@Schema(example = "CRIADO")
	private StatusPedido status;
	
	@Schema(example = "2019-12-01T00:00:00Z")
	private OffsetDateTime dataCriacao;
	
	private RestauranteApenasNomeDTO restaurante;
	
	private UsuarioDTO cliente;	
	
	@Schema(name = "_links")
	private LinksModelOpenApi _links;
}
