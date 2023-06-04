package com.algafood.api.v1.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.algafood.api.v1.openapi.model.ItemPedidoModelOpenApi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "itensPedido")
@Getter
@Setter
@Schema(implementation = ItemPedidoModelOpenApi.class)
public class ItemPedidoDTO extends RepresentationModel<ItemPedidoDTO> {

	private Long produtoId;
	
	private String produtoNome;
	
	private Integer quantidade;
	
	private BigDecimal precoUnitario;
	
	private BigDecimal precoTotal;
	
	private String observacao;
}
