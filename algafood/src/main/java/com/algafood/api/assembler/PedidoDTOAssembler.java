package com.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.controller.CidadeController;
import com.algafood.api.controller.FormaPagamentoController;
import com.algafood.api.controller.PedidoController;
import com.algafood.api.controller.RestauranteController;
import com.algafood.api.controller.RestauranteProdutoController;
import com.algafood.api.controller.UsuarioController;
import com.algafood.api.model.PedidoDTO;
import com.algafood.api.utils.AlgaLinks;
import com.algafood.domain.model.Pedido;

@Component
public class PedidoDTOAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public PedidoDTOAssembler() {
		super(PedidoController.class, PedidoDTO.class);
	}
	
	@Override
	public PedidoDTO toModel(Pedido pedido) {
		PedidoDTO pedidoDTO = createModelWithId(pedido.getCodigo(), pedido);
		
		modelMapper.map(pedido, pedidoDTO);
		
		pedidoDTO.getRestaurante().add(linkTo(
					methodOn(RestauranteController.class).buscar(pedidoDTO.getRestaurante().getId())).withSelfRel());
		
		pedidoDTO.getCliente().add(linkTo(
				methodOn(UsuarioController.class).buscarPorId(pedidoDTO.getCliente().getId())).withSelfRel());
		
		pedidoDTO.getFormaPagamento().add(linkTo(
				methodOn(FormaPagamentoController.class)
						.buscar(pedido.getFormaPagamento().getId(), null)).withSelfRel());
		
		pedidoDTO.getEnderecoEntrega().getCidade().add(linkTo(
				methodOn(CidadeController.class)
						.buscar(pedidoDTO.getEnderecoEntrega().getCidade().getId())).withSelfRel());
		
		
		pedidoDTO.getItens().forEach(item -> {
			item.add(linkTo(methodOn(RestauranteProdutoController.class)
					.buscarPorId(pedidoDTO.getRestaurante().getId(), item.getProdutoId())).withRel("produto"));
		});
		
		pedidoDTO.add(algaLinks.linkToPedidos());
		
		return pedidoDTO;
	}
}
