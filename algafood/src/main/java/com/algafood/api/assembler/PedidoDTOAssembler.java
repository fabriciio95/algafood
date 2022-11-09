package com.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.controller.PedidoController;
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
		
		pedidoDTO.getRestaurante().add(algaLinks.linkToRestaurante(pedidoDTO.getRestaurante().getId()));
		
		pedidoDTO.getCliente().add(algaLinks.linkToUsuario(pedidoDTO.getCliente().getId()));
		
		pedidoDTO.getFormaPagamento().add(algaLinks.linkToFormaPagamento(pedidoDTO.getFormaPagamento().getId()));
		
		pedidoDTO.getEnderecoEntrega().getCidade().add(
				algaLinks.linkToCidade(pedidoDTO.getEnderecoEntrega().getCidade().getId()));
		
		pedidoDTO.getItens().forEach(item -> {
			item.add(algaLinks.linkToProduto(pedidoDTO.getRestaurante().getId(), item.getProdutoId()));
		});
		
		pedidoDTO.add(algaLinks.linkToPedidos());
		
		if(pedido.podeSerConfirmado())
			pedidoDTO.add(algaLinks.linkToConfirmacaoPedido(pedidoDTO.getCodigo(), "confirmar"));
		
		if(pedido.podeSerCancelado())
			pedidoDTO.add(algaLinks.linkToCancelamentoPedido(pedidoDTO.getCodigo(), "cancelar"));
		
		if(pedido.podeSerEntregue())
			pedidoDTO.add(algaLinks.linkToEntregaPedido(pedidoDTO.getCodigo(), "entregar"));
		
		return pedidoDTO;
	}
}
