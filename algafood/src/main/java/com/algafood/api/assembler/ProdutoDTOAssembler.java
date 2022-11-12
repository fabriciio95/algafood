package com.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.controller.RestauranteProdutoController;
import com.algafood.api.model.ProdutoDTO;
import com.algafood.api.utils.AlgaLinks;
import com.algafood.domain.model.Produto;

@Component
public class ProdutoDTOAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	private Long restauranteId;
	
	public ProdutoDTOAssembler() {
		super(RestauranteProdutoController.class, ProdutoDTO.class);
	}
	
	public ProdutoDTO toModel(Produto produto) {
		ProdutoDTO produtoDTO = createModelWithId(produto.getId(), produto, produto.getRestaurante().getId());
		
		modelMapper.map(produto, produtoDTO);
		
		produtoDTO.add(algaLinks.linkToRestauranteProdutos(produto.getRestaurante().getId(), "produtos"));
		
		restauranteId = produto.getRestaurante().getId();
		
		return produtoDTO;
	}
	
	@Override
	public CollectionModel<ProdutoDTO> toCollectionModel(Iterable<? extends Produto> entities) {
		return super.toCollectionModel(entities)
				.add(algaLinks.linkToRestauranteProdutos(restauranteId));
	}
}

