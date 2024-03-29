package com.algafood.api.v1.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.algafood.api.v1.openapi.model.PedidoResumoModelOpenApi;
import com.algafood.domain.model.StatusPedido;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
@Getter
@Setter
@Schema(implementation = PedidoResumoModelOpenApi.class)
public class PedidoResumoDTO extends RepresentationModel<PedidoResumoDTO> {

	private String codigo;
	
	private BigDecimal subtotal;
	
	private BigDecimal taxaFrete;
	
	private BigDecimal valorTotal;
	
	private StatusPedido status;
	
	private OffsetDateTime dataCriacao;
	
	private RestauranteApenasNomeDTO restaurante;
	
	private UsuarioDTO cliente;	
	
}
