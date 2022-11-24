package com.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.v1.controller.RestauranteController;
import com.algafood.api.v1.model.RestauranteBasicoDTO;
import com.algafood.api.v1.utils.AlgaLinks;
import com.algafood.domain.model.Restaurante;

@Component
public class RestauranteBasicoDTOAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteBasicoDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	
	public RestauranteBasicoDTOAssembler() {
		super(RestauranteController.class, RestauranteBasicoDTO.class);
	}

	@Override
	public RestauranteBasicoDTO toModel(Restaurante restaurante) {
		RestauranteBasicoDTO restauranteBasicoDTO = createModelWithId(restaurante.getId(), restaurante);
		
		modelMapper.map(restaurante, restauranteBasicoDTO);
		
		restauranteBasicoDTO.getCozinha().add(algaLinks.linkToCozinha(restauranteBasicoDTO.getCozinha().getId()));
		
		restauranteBasicoDTO.add(algaLinks.linkToRestaurantes("restaurantes"));
		
		return restauranteBasicoDTO;
	}
	
	@Override
	public CollectionModel<RestauranteBasicoDTO> toCollectionModel(Iterable<? extends Restaurante> entities) {
		return super.toCollectionModel(entities)
				.add(algaLinks.linkToRestaurantes());
	}

}
