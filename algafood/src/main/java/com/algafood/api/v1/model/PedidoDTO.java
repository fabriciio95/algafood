package com.algafood.api.v1.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.algafood.api.v1.openapi.model.PedidoModelOpenApi;
import com.algafood.domain.model.StatusPedido;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(implementation = PedidoModelOpenApi.class)
public class PedidoDTO extends RepresentationModel<PedidoDTO> {

	private String codigo;
	
	private BigDecimal subtotal;
	
	private BigDecimal taxaFrete;
	
	private BigDecimal valorTotal;
	
	private StatusPedido status;
	
	private OffsetDateTime dataCriacao;
	
	private OffsetDateTime dataConfirmacao;
	
	private OffsetDateTime dataEntrega;
	
	private OffsetDateTime dataCancelamento;
	
	private RestauranteApenasNomeDTO restaurante;
	
	private UsuarioDTO cliente;
	
	private FormaPagamentoDTO formaPagamento;
	
	private EnderecoDTO enderecoEntrega;
	
	private List<ItemPedidoDTO> itens;
	
}
