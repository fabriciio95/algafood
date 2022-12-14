package com.algafood.api.v1.model.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInputDTO {

	@Valid
	@NotNull
	private RestauranteIdInputDTO restaurante;
	
	@Valid
	@NotNull
	private FormaPagamentoIdInputDTO formaPagamento;
	
	@Valid
	@NotNull
	private EnderecoInputDTO enderecoEntrega;
	
	@Valid
	@NotNull
	@Size(min = 1)
	private List<ItemPedidoInputDTO> itens;
}