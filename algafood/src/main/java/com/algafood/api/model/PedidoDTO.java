package com.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.algafood.domain.model.StatusPedido;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDTO extends RepresentationModel<PedidoDTO> {

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
	
	@ApiModelProperty(example = "2022-10-20T10:00:00Z")
	private OffsetDateTime dataConfirmacao;
	
	@ApiModelProperty(example = "2022-10-20T10:00:00Z")
	private OffsetDateTime dataEntrega;
	
	@ApiModelProperty(example = "2022-10-20T10:00:00Z")
	private OffsetDateTime dataCancelamento;
	
	private RestauranteResumoDTO restaurante;
	
	private UsuarioDTO cliente;
	
	private FormaPagamentoDTO formaPagamento;
	
	private EnderecoDTO enderecoEntrega;
	
	private List<ItemPedidoDTO> itens;
	
}
