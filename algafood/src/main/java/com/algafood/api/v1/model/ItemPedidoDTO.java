package com.algafood.api.v1.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "itensPedido")
@Getter
@Setter
public class ItemPedidoDTO extends RepresentationModel<ItemPedidoDTO> {

	@ApiModelProperty(example = "1")
	private Long produtoId;
	
	@ApiModelProperty(example = "Murg Curry")
	private String produtoNome;
	
	@ApiModelProperty(example = "1")
	private Integer quantidade;
	
	@ApiModelProperty(example = "33.50")
	private BigDecimal precoUnitario;
	
	@ApiModelProperty(example = "33.50")
	private BigDecimal precoTotal;
	
	@ApiModelProperty(example = "Menos picante, por favor")
	private String observacao;
}
