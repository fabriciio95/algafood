package com.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algafood.domain.model.StatusPedido;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResumoDTO {

	@ApiModelProperty(example = "313c828d-5787-4689-aaf4-e94678446682")
	private String codigo;
	
	@ApiModelProperty(example = "56.90")
	private BigDecimal subtotal;
	
	@ApiModelProperty(example = "10.00")
	private BigDecimal taxaFrete;
	
	@ApiModelProperty(example = "66.90")
	private BigDecimal valorTotal;
	
	@ApiModelProperty(example = "CONFIRMADO")
	private StatusPedido status;
	
	@ApiModelProperty(example = "2022-10-20T10:00:00Z")
	private OffsetDateTime dataCriacao;
	
	private RestauranteResumoDTO restaurante;
	
	private UsuarioDTO cliente;	
	
}
