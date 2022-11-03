package com.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.controller.PedidoController;
import com.algafood.api.controller.RestauranteController;
import com.algafood.api.controller.UsuarioController;
import com.algafood.api.model.PedidoResumoDTO;
import com.algafood.domain.model.Pedido;

@Component
public class PedidoResumoDTOAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoDTO> {

	@Autowired
	private ModelMapper modelMapper;

	public PedidoResumoDTOAssembler() {
		super(PedidoController.class, PedidoResumoDTO.class);
	}

	@Override
	public PedidoResumoDTO toModel(Pedido pedido) {
		PedidoResumoDTO pedidoResumoDTO = createModelWithId(pedido.getCodigo(), pedido);

		modelMapper.map(pedido, pedidoResumoDTO);

		pedidoResumoDTO.getRestaurante().add(
				linkTo(methodOn(RestauranteController.class)
						.buscar(pedidoResumoDTO.getRestaurante().getId())).withSelfRel());

		pedidoResumoDTO.getCliente().add(
				linkTo(methodOn(UsuarioController.class)
						.buscarPorId(pedidoResumoDTO.getCliente().getId())).withSelfRel());

		pedidoResumoDTO.add(linkTo(PedidoController.class).withRel("pedidos"));
		
		return pedidoResumoDTO;
	}

}
