package com.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.v1.controller.RestauranteProdutoController;
import com.algafood.api.v1.model.ProdutoDTO;
import com.algafood.api.v1.utils.AlgaLinks;
import com.algafood.core.security.AlgaSecurity;
import com.algafood.domain.model.Produto;

@Component
public class ProdutoDTOAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
		
	public ProdutoDTOAssembler() {
		super(RestauranteProdutoController.class, ProdutoDTO.class);
	}
	
	public ProdutoDTO toModel(Produto produto) {
		ProdutoDTO produtoDTO = createModelWithId(produto.getId(), produto, produto.getRestaurante().getId());
		
		modelMapper.map(produto, produtoDTO);
		
		if(algaSecurity.podeConsultarRestaurantes()) {
			produtoDTO.add(algaLinks.linkToRestauranteProdutos(produto.getRestaurante().getId(), "produtos"));
			
			produtoDTO.add(algaLinks.linkToFotoProduto(produto.getRestaurante().getId(),
					produto.getId(), "foto"));
		}
				
		return produtoDTO;
	}
}

