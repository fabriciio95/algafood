package com.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algafood.domain.model.StatusPedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResumoDTO {

	private Long id;
	
	private BigDecimal subtotal;
	
	private BigDecimal taxaFrete;
	
	private BigDecimal valorTotal;
	
	private StatusPedido status;
	
	private OffsetDateTime dataCriacao;
	
	private RestauranteResumoDTO restaurante;
	
	private UsuarioDTO cliente;	
}
