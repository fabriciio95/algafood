package com.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.v1.controller.PedidoController;
import com.algafood.api.v1.model.PedidoResumoDTO;
import com.algafood.api.v1.utils.AlgaLinks;
import com.algafood.core.security.AlgaSecurity;
import com.algafood.domain.model.Pedido;

@Component
public class PedidoResumoDTOAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;

	public PedidoResumoDTOAssembler() {
		super(PedidoController.class, PedidoResumoDTO.class);
	}

	@Override
	public PedidoResumoDTO toModel(Pedido pedido) {
		PedidoResumoDTO pedidoResumoDTO = createModelWithId(pedido.getCodigo(), pedido);

		modelMapper.map(pedido, pedidoResumoDTO);

		if(algaSecurity.podeConsultarRestaurantes())
			pedidoResumoDTO.getRestaurante().add(algaLinks.linkToRestaurante(pedidoResumoDTO.getRestaurante().getId()));

		if(algaSecurity.podeConsultarUsuariosGruposPermissoes())
			pedidoResumoDTO.getCliente().add(algaLinks.linkToUsuario(pedidoResumoDTO.getCliente().getId()));

		if(algaSecurity.podePesquisarPedidos())
			pedidoResumoDTO.add(algaLinks.linkToPedidos("pedidos"));
		
		return pedidoResumoDTO;
	}

}
