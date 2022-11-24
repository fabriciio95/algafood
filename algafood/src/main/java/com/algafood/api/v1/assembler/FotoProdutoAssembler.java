package com.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.v1.controller.RestauranteProdutoFotoController;
import com.algafood.api.v1.model.FotoProdutoDTO;
import com.algafood.api.v1.utils.AlgaLinks;
import com.algafood.domain.model.FotoProduto;

@Component
public class FotoProdutoAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public FotoProdutoAssembler() {
		super(RestauranteProdutoFotoController.class, FotoProdutoDTO.class);
	}
	
	@Override
	public FotoProdutoDTO toModel(FotoProduto foto) {
		FotoProdutoDTO fotoProdutoDTO = modelMapper.map(foto, FotoProdutoDTO.class);
	
		modelMapper.map(foto, fotoProdutoDTO);
		
		fotoProdutoDTO.add(algaLinks.linkToFotoProduto(foto.getRestauranteId(), foto.getProduto().getId()));
		
		fotoProdutoDTO.add(algaLinks.linkToRestauranteProduto(foto.getRestauranteId(), foto.getId(), "produto"));
		
		return fotoProdutoDTO;
	}
}
