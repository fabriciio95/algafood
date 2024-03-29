package com.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.v1.controller.RestauranteController;
import com.algafood.api.v1.model.RestauranteApenasNomeDTO;
import com.algafood.api.v1.utils.AlgaLinks;
import com.algafood.core.security.AlgaSecurity;
import com.algafood.domain.model.Restaurante;

@Component
public class RestauranteApenasNomeDTOAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteApenasNomeDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	
	public RestauranteApenasNomeDTOAssembler() {
		super(RestauranteController.class, RestauranteApenasNomeDTO.class);
	}

	@Override
	public RestauranteApenasNomeDTO toModel(Restaurante restaurante) {
		RestauranteApenasNomeDTO restauranteApenasNomeDTO = createModelWithId(restaurante.getId(), restaurante);
		
		modelMapper.map(restaurante, restauranteApenasNomeDTO);
		
		if(algaSecurity.podeConsultarRestaurantes())
			restauranteApenasNomeDTO.add(algaLinks.linkToRestaurantes("restaurantes"));
		
		return restauranteApenasNomeDTO;
	}
	
	@Override
	public CollectionModel<RestauranteApenasNomeDTO> toCollectionModel(Iterable<? extends Restaurante> entities) {
		CollectionModel<RestauranteApenasNomeDTO> collectionDto = super.toCollectionModel(entities);
			
		if(algaSecurity.podeConsultarRestaurantes())
				collectionDto.add(algaLinks.linkToRestaurantes());
		
		return collectionDto;
	}
}
