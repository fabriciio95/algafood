package com.algafood.api.v1.openapi.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.algafood.api.v1.model.EnderecoDTO;
import com.algafood.api.v1.model.FormaPagamentoDTO;
import com.algafood.api.v1.model.ItemPedidoDTO;
import com.algafood.api.v1.model.RestauranteApenasNomeDTO;
import com.algafood.api.v1.model.UsuarioDTO;
import com.algafood.domain.model.StatusPedido;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "PedidoDTO")
public class PedidoModelOpenApi {

	@Schema(example = "8f2d22a7-9f3b-421e-9377-4c3b77b34924")
	private String codigo;
	
	@Schema(example = "22.39")
	private BigDecimal subtotal;
	
	@Schema(example = "12.33")
	private BigDecimal taxaFrete;
	
	@Schema(example = "42.23")
	private BigDecimal valorTotal;
	
	@Schema(example = "CRIADO")
	private StatusPedido status;
	
	@Schema(example = "2019-12-01T10:00:00Z")
	private OffsetDateTime dataCriacao;
	
	@Schema(example = "2019-12-01T10:00:04Z")
	private OffsetDateTime dataConfirmacao;
	
	@Schema(example = "2019-12-01T11:03:00Z")
	private OffsetDateTime dataEntrega;
	
	@Schema(example = "2019-12-01T08:00:00Z")
	private OffsetDateTime dataCancelamento;
	
	private RestauranteApenasNomeDTO restaurante;
	
	private UsuarioDTO cliente;
	
	private FormaPagamentoDTO formaPagamento;
	
	private EnderecoDTO enderecoEntrega;
	
	private List<ItemPedidoDTO> itens;
	
	@Schema(name = "_links")
	private LinksModelOpenApi _links;
}
